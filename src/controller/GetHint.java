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
    private final Controller controller;

    public GetHint(Controller controller) {
        this.mv = controller.mv;
        this.model = controller.model;
        this.controller = controller;

    }

    public void actionPerformed(ActionEvent e) {
        mv.ifPresent(view -> {
            Course selectedCourse =  model.getSelectedCourse();
            if (selectedCourse == null) {
                return;
            }

            if (!model.getHint()) {
                controller.activate(new String[]{}, new String[]{"HINT"});

            }
            else
                controller.activate(new String[]{"HINT"}, new String[]{});




            Exercise currentExercise = selectedCourse.getExercises().get(view.getCurrentExerciseIndex());
            String hintMessage = model.getHintForExercise(currentExercise);

            view.displayHint(hintMessage);
        });
    }
}
