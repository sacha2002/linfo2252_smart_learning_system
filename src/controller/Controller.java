package controller;

import models.Exercises.Exercise;
import models.Exercises.ExerciseData;
import models.courses.Course;
import models.courses.French;
import models.courses.Spanish;
import views.MainView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

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
        return false;
    }

    @Override
    public boolean disableUIView() {
        return false;
    }

    @Override
    public String[] getStateAsLog() {
        return new String[0];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String course;
        Controller controller = new Controller();
        // Boucle infinie pour demander l'entrée
        while (true) {
            System.out.print("Which courses do you want to learn?, type exit to quit     ");
            course = scanner.nextLine();

            // Vérifier si l'utilisateur souhaite quitter
            if (course.equalsIgnoreCase("exit")) {
                System.out.println("program finished!");
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
                    }
                } else {
                    System.out.println("Sorry, that course is not available.");
                }
            }

        }

        scanner.close();

    }
}