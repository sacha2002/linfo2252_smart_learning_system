package controller;

import models.Exercises.Exercise;
import models.Exercises.ExerciseData;
import models.Logger;
import models.courses.Course;
import models.courses.French;
import models.courses.Spanish;
import views.MainView;

import java.util.*;

public class Controller implements ControllerInterface{

    Map<String, Course> available_courses =  new HashMap<>();
    Optional<MainView> mv = Optional.empty();

    public Controller(){

        available_courses.putIfAbsent("spanish",new Spanish(1200,ExerciseData.getSpanishGoldExercises()));
        available_courses.putIfAbsent("french",new French(1200,ExerciseData.getFrenchGoldExercises()));
    }

    @Override
    public int activate(String[] deactivations, String[] activations) {
        return 0;
    }

    @Override
    public boolean enableUIView() {
       mv =  Optional.of(new MainView());
        return true;
    }

    @Override
    public boolean disableUIView() {
        mv.ifPresent(MainView::closeWindow);
        mv = Optional.empty();
        return true;
    }

    @Override
    public String[] getStateAsLog() {
        List<String> currentStateLogs = Logger.getInstance().getAllCurrentStates();
        return currentStateLogs.toArray(new String[0]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String course;
        Controller controller = new Controller();
        // Boucle infinie pour demander l'entrée
        while (true) {
            System.out.print("Which courses do you want to learn?, type exit to quit ");
            course = scanner.nextLine();

            // Vérifier si l'utilisateur souhaite quitter
            if (course.equalsIgnoreCase("exit")) {
                System.out.println("program finished!");
                controller.disableUIView();
                break;
            } else
            if (course.equalsIgnoreCase("view")) {
                controller.enableUIView();

            } else {

                Course selectedCourse = controller.available_courses.get(course);

                if (selectedCourse != null) {
                    // Course found, print exercises
                    controller.mv.ifPresent(MainView::clearLabels);
                    System.out.println("Exercises for " + selectedCourse.getName() + ":");
                    for (Exercise exercise : selectedCourse.getExercises()) {
                        controller.mv.ifPresent(mv -> mv.displayExercise(exercise.getText()));
                        System.out.println("- " + exercise.getText());
                        System.out.print("please write next to continue : ");
                        String input = scanner.nextLine();


                        while (!input.equalsIgnoreCase("next")) {
                            input = scanner.nextLine();
                        }




                    }
                } else {
                    System.out.println("Sorry, that course is not available.");
                }
            }

        }
        scanner.close();

    }
}
