package controller;

import models.Exercises.Exercise;
import models.Model;
import models.courses.Course;
import views.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

//could use the mediator pattern but its just one variable that they share, so NO (T.A)

public class ChooseCourse implements ActionListener {
    private final Map<JButton, String> courseMap = new HashMap<>();
    private final Optional<MainView> mv;
    private final Model model;
    private final Controller controller;

    private Course selectedCourse;

    public ChooseCourse(Controller controller) {
        this.mv = controller.mv;
        this.model = controller.model;
        this.controller = controller;

        this.selectedCourse = model.getSelectedCourse();
        mv.ifPresent(view -> {
            courseMap.put(view.getSpanishButton(), "SPANISH");
            courseMap.put(view.getFrenchButton(), "FRENCH");
            courseMap.put(view.getEnglishButton(), "ENGLISH");
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mv.ifPresent(view -> {
            JButton sourceButton = (JButton) e.getSource();
            String chosenCourse = courseMap.get(sourceButton);

            if(selectedCourse !=null && selectedCourse.getName().equals(chosenCourse)){
                controller.activate( new String[]{chosenCourse},new String[]{});
                return;
            }

            if (chosenCourse != null ) {
                String[] deactivateCurrentCourse = selectedCourse != null ? new String[]{selectedCourse.getName()} : new String[]{};
                controller.activate(deactivateCurrentCourse, new String[]{chosenCourse});
                view.setCourse(chosenCourse);
                updateSelectedCourse(chosenCourse, view);
                displayFirstExercise(view);
            }
        });
    }

    private void updateSelectedCourse(String chosenCourse, MainView view) {

        if (selectedCourse == null || !Objects.equals(chosenCourse, selectedCourse.getName())) {
            selectedCourse = model.getCourse(chosenCourse);
            model.setSelectedCourse(selectedCourse);
            if (selectedCourse != null) {
                selectedCourse.addRankObserver(view.getRankLabelObserver());
                selectedCourse.addScoreObserver(view.getScoreLabelObserver());
            }
        }
    }

    private void displayFirstExercise(MainView view) {
        if (selectedCourse != null && model.getCoursesList().contains(selectedCourse)) {
            Exercise currentExercise = selectedCourse.getExercises().get(view.getCurrentExerciseIndex());
            view.displayExercise(currentExercise, selectedCourse.getExcerciseIndex(currentExercise));
            view.getAnswerField().revalidate();
            view.getAnswerField().repaint();
        }
    }
}


