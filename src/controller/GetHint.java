package controller;

import models.Exercises.Exercise;
import models.Model;
import models.courses.Course;
import views.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class GetHint implements ActionListener {

    private final Optional<MainView> mv;
    private final Model model;

    public GetHint(Optional<MainView> mv, Model model) {
        this.mv = mv;
        this.model = model;

    }

    public void actionPerformed(ActionEvent e) {
        mv.ifPresent(view -> {
            Course selectedCourse =  model.getSelectedCourse();

            if (selectedCourse == null) {
                return;
            }

            Exercise currentExercise = selectedCourse.getExercises().get(view.getCurrentExerciseIndex());
            String hintMessage = model.getHintForExercise(currentExercise);

            System.out.println(hintMessage);
            view.displayHint(hintMessage);
        });
    }
}
