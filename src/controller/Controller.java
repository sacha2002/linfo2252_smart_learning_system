package controller;

import models.Exercises.Exercise;
import models.Logger;
import models.Model;
import models.courses.Course;
import models.features.FeatureCommand;
import views.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
;
public class Controller implements ControllerInterface{

    private int exercice_id;
    private int points = 0;
    private String real_answer;
    private Exercise current_exercice;




        public class passToPremium implements ActionListener {
            private boolean isPremium = false;
            public void actionPerformed(ActionEvent e) {
                mv.ifPresent(mv -> {
                    if (e.getSource() == mv.getPremiumButton()) {
                        if (isPremium) {
                            isPremium = false;
                            activate(new String[]{"PREMIUM"}, new String[]{});
                            mv.setPremiumLabel("Free Mode");
                        } else {
                            isPremium = true;
                            activate(new String[]{}, new String[]{"PREMIUM"});
                            mv.setPremiumLabel("Premium Mode");
                        }

                    }
                });
            }
        }

        public class chooseCourse implements ActionListener {
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
                    mv.setCourse(course);
                    Course selectedCourse = model.getCourse(course);
                    if (selectedCourse != null && model.getCoursesList().contains(selectedCourse)) {
                        current_exercice = selectedCourse.getExercises().get(mv.getCurrentExerciceIndex());
                        mv.displayExercise(current_exercice);
                        mv.getAnswerField().revalidate();
                        mv.getAnswerField().repaint();
                    }
                });
            }
        }

        public class enterAnswer implements ActionListener {

            static String answer = "";

            public void actionPerformed(ActionEvent e) {
                mv.ifPresent(mv -> {
                    answer = mv.getAnswerField().getText();
                    real_answer = current_exercice.getAnswer();
                    if (answer.equals(real_answer)) {

                        switch (current_exercice.getRank()) {
                            case BRONZE:
                                points += 1;
                                break;
                            case SILVER:
                                points += 3;
                                break;
                            case GOLD:
                                points += 5;
                                break;
                        }
                        mv.updateScore(points);
                    }
                });
            }
        }

        public class activateCourse implements ActionListener {
            private boolean isActivated = false;
            public void actionPerformed(ActionEvent e) {
                mv.ifPresent(mv -> {
                    if (e.getSource() == mv.getActivateCourseButton()) {
                        if (isActivated) {
                            isActivated = false;
                            activate(new String[]{"COURSES"}, new String[]{});

                        } else {
                            isActivated = true;
                            activate(new String[]{}, new String[]{"COURSES"});
                        }

            }});
            }
            }

        public class changeQuestion implements ActionListener {
            public void actionPerformed(ActionEvent e) {

                mv.ifPresent(mv -> {
                    if (!model.getAvailableCourses().contains(mv.getCourse())) {
                        return;
                    }
                    int index = mv.getCurrentExerciceIndex();
                    if (e.getSource() == mv.getPreviousQuestionButton()) {
                        if (index > 0) {
                            index--;
                            mv.displayExercise(model.getCourse(mv.getCourse()).getExercises().get(index));
                        }
                    } else if (e.getSource() == mv.getNextQuestionButton()) {
                        Course course = model.getCourse(mv.getCourse());
                        if (course != null && index < course.getExercises().size() - 1) {
                            index++;
                            mv.displayExercise(model.getCourse(mv.getCourse()).getExercises().get(index));
                        }
                    }
                    ;

                });
            }
        }

        Optional<MainView> mv = Optional.empty();
        Model model = new Model("OEM", 0, 30);


        public Controller() {
        }

        @Override
        public int activate(String[] deactivations, String[] activations) {
            for (String feat : activations) {
                System.out.println(feat);
                model.activateFeature(feat);
            }

            for (String feat : deactivations) {
                model.deactivateFeature(feat);
            }
            return 0;
        }


        @Override
        public boolean enableUIView() {
            mv = Optional.of(new MainView());
            mv.get().addCourseButtonListener(new chooseCourse());
            mv.get().addTextfieldListener(new enterAnswer());
            mv.get().addChangeQuestionButtonListener(new changeQuestion());
            mv.get().addPremiumButtonListener(new passToPremium());
            mv.get().addActivateCourseButtonListener(new activateCourse());
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

        public static void main(String[] args) throws Exception {
            Controller controller = new Controller();
            controller.enableUIView();
            controller.activate(new String[]{"PREMIUM"}, new String[]{});
        }
    }
