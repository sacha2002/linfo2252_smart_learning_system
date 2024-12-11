package controller;

import models.Exercises.Exercise;
import models.Logger;
import models.Model;
import models.Exercises.Rank;
import models.courses.Course;
import views.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
;
public class Controller implements ControllerInterface{




        public class passToPremium implements ActionListener {
            private boolean isPremium = true;
            public void actionPerformed(ActionEvent e) {
                mv.ifPresent(mv -> {
                    if (e.getSource() == mv.getPremiumButton()) {
                        if (isPremium) {
                            isPremium = false;
                            activate(new String[]{"PREMIUM"}, new String[]{});

                            model.getEnergySystem().addObserver(mv.getPremiumLabelObserver());

                        } else {
                            isPremium = true;
                            activate(new String[]{}, new String[]{"PREMIUM"});
                            model.getEnergySystem().removeObserver(mv.getPremiumLabelObserver());

                        }

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
                    int index = mv.getCurrentExerciseIndex();

                    if (e.getSource() == mv.getPreviousQuestionButton()) {
                        if (index > 0) {
                            index--;

                            Course course = model.getCourse(mv.getCourse());
                            Exercise currentExercice = course.getExercises().get(index);
                            mv.displayExercise(currentExercice,course.getExcerciseIndex(currentExercice));
                            mv.displayHint("");
                        }
                    } else if (e.getSource() == mv.getNextQuestionButton()) {
                        Course course = model.getCourse(mv.getCourse());

                        if (course != null && index < course.getExercises().size() - 1) {
                            index++;
                            Exercise currentExercice =course.getExercises().get(index);
                            mv.displayExercise(currentExercice,course.getExcerciseIndex(currentExercice));
                            mv.displayHint("");
                        }
                    }
                    ;

                });
            }
        }

        Optional<MainView> mv = Optional.empty();
        Model model = new Model("OEM", 0);


        public Controller() {
        }

        @Override
        public int activate(String[] deactivations, String[] activations) {
            for (String feat : activations) {
                System.out.println("activated: " + feat);
                model.activateFeature(feat);
            }

            for (String feat : deactivations) {
                System.out.println("deactivated: " + feat);
                model.deactivateFeature(feat);
            }
            return 0;
        }


        @Override
        public boolean enableUIView() {
            mv = Optional.of(new MainView());
            mv.get().addCourseButtonListener(new ChooseCourse(mv,model,this));
            mv.get().addTextfieldListener(new EnterAnswer(mv,model));
            mv.get().addHintButtonListener( new GetHint(mv,model));
            mv.get().addChangeQuestionButtonListener(new changeQuestion());
            mv.get().addPremiumButtonListener(new passToPremium());
            mv.get().addActivateCourseButtonListener(new activateCourse());
            model.getStreak().addObserver(mv.get().getStreakLabelObserver());
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
            controller.activate(new String[]{}, new String[]{"PREMIUM"});
        }
    }
