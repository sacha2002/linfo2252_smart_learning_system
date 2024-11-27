package controller;

import models.Exercises.Exercise;
import models.Logger;
import models.Model;
import models.Rank;
import models.courses.Course;
import models.features.FeatureCommand;
import views.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
;
public class Controller implements ControllerInterface{

    private int exercice_id;
    private String real_answer;
    private Course selectedCourse;


        public class passToPremium implements ActionListener {
            private boolean isPremium = true;
            public void actionPerformed(ActionEvent e) {
                mv.ifPresent(mv -> {
                    if (e.getSource() == mv.getBottomPanel().getPremiumButton()) {
                        if (isPremium) {
                            isPremium = false;
                            activate(new String[]{"PREMIUM"}, new String[]{});
                            model.getEnergySystem().addObserver(mv.getPremiumLabelObserver());
                            //initial state
                            mv.setPremiumLabel("Free Mode: "+ model.getEnergySystem().getCurrentEnergy()+"/"+model.getEnergySystem().getMaxEnergy());
                        } else {
                            isPremium = true;
                            activate(new String[]{}, new String[]{"PREMIUM"});
                            model.getEnergySystem().removeObserver(mv.getPremiumLabelObserver());
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
                    if(selectedCourse == null || !Objects.equals(course, selectedCourse.getName())){
                        selectedCourse = model.getCourse(course);
                        selectedCourse.addObserver(mv.getRankLabelObserver());
                    }

                    if (selectedCourse != null && model.getCoursesList().contains(selectedCourse)) {
                        Exercise current_exercice = selectedCourse.getExercises().get(mv.getCurrentExerciceIndex());
                        mv.displayExercise(current_exercice,selectedCourse.getExcerciseIndex(current_exercice));

                        mv.updateRank(Rank.getRankByNumber(selectedCourse.getCourseRank()).getName());
                        mv.updateScore(selectedCourse.getCourseRank());
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
                    if( !model.practice()){
                            return;
                    }
                    Exercise current_exercice = selectedCourse.getExercises().get(mv.getCurrentExerciceIndex());
                    if (selectedCourse.practice(current_exercice,answer)) {
                        mv.updateScore(selectedCourse.getCourseRank());
                    }
                });
            }
        }

    public class getHint implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            mv.ifPresent(mv -> {
                Exercise current_exercice = selectedCourse.getExercises().get(mv.getCurrentExerciceIndex());
                if (model.isPremium()) {
                    System.out.println(current_exercice.getHint());
                }else{
                    System.out.println("Pay us for hint");
                }
            });
        }
    }

        public class activateCourse implements ActionListener {
            private boolean isActivated = false;
            public void actionPerformed(ActionEvent e) {
                mv.ifPresent(mv -> {
                    if (e.getSource() == mv.getBottomPanel().getActivateCourseButton()) {
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
                    int index = mv.getMiddlePanel().getCurrentExerciseIndex();

                    if (e.getSource() == mv.getMiddlePanel().getPreviousQuestionButton()) {
                        if (index > 0) {
                            index--;

                            Course course = model.getCourse(mv.getCourse());
                            Exercise current_exercice = course.getExercises().get(index);
                            mv.displayExercise(current_exercice,course.getExcerciseIndex(current_exercice));
                        }
                    } else if (e.getSource() == mv.getMiddlePanel().getNextQuestionButton()) {
                        Course course = model.getCourse(mv.getCourse());

                        if (course != null && index < course.getExercises().size() - 1) {
                            index++;
                            Exercise current_exercice =course.getExercises().get(index);
                            mv.displayExercise(current_exercice,course.getExcerciseIndex(current_exercice));
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
            mv.get().addHintButtonListener( new getHint());
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
            controller.activate(new String[]{}, new String[]{"PREMIUM"});
        }
    }
