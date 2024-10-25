package controller;

import models.Exercises.Exercise;
import models.Logger;
import models.Model;
import models.courses.Course;
import views.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Controller implements ControllerInterface{

    private int exercice_id;
    private String real_answer;

    public class clickButton implements ActionListener {
        static String course = "";

        public void actionPerformed(ActionEvent e) {
            mv.ifPresent(mv -> {

                if (e.getSource() == mv.getSpanishButton()) {
                    course = "spanish";
                } else if (e.getSource() == mv.getFrenchButton()) {
                    course = "french";
                } else if (e.getSource() == mv.getEnglishButton()) {
                    course = "english";
                }
                Course selectedCourse = model.getCourse(course);
                for (Exercise exercise : selectedCourse.getExercises()){
                    exercice_id = exercise.getId();
                    real_answer = exercise.getAnswer();
                    mv.displayExercise(exercise.getText());
            };
        });
        }
    }

    public class enterTextfield implements ActionListener {

        static String answer = "";
        public void actionPerformed(ActionEvent e){
            System.out.println("okok");
            mv.ifPresent(mv -> {
                answer = mv.getUserAnswer();
                if (answer.equals(real_answer)){
                    System.out.println("bonne réponse");
                }
                else {
                    System.out.println("Reessayez");
                }


            });
        }
    }

    List<String> available_courses =  new ArrayList<>();
    Optional<MainView> mv = Optional.empty();
    Model model = new Model("OEM",0,false,30);



    public Controller(){
        available_courses.add("english");
        available_courses.add("spanish");
        available_courses.add("french");
    }

    @Override
    public int activate(String[] deactivations, String[] activations) {
        for(String feat : activations){
            if(feat.equals("courses")){
                available_courses.add("english");
                available_courses.add("spanish");
                available_courses.add("french");
            } else if( feat.equals("premium")){
                model.setPremium(true);
            }
        }

        for(String feat : deactivations){
            if(feat.equals("courses")){
                available_courses.remove("english");
                available_courses.remove("spanish");
                available_courses.remove("french");
            } else if( feat.equals("premium")){
                model.setPremium(false);
            }
        }

        return 0;
        //energy system if premium or not
        //courses if energy is 0
    }

    @Override
    public boolean enableUIView() {
       mv =  Optional.of(new MainView());
       mv.get().addButtonsLister(new clickButton());
       mv.get().addTextfieldListener(new enterTextfield());
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
        controller.enableUIView();

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


            } else {

                Course selectedCourse = controller.model.getCourse(course);

                if (selectedCourse != null && controller.available_courses.contains(course)) {
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
                    System.out.println("Sorry, that course is not available. check your energy levels");
                }
            }

        }
        scanner.close();

    }
}
