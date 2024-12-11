package controller;

import models.Exercises.Exercise;
import models.Model;
import models.courses.Course;
import views.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class ChangeQuestion implements ActionListener {

    private final Optional<MainView> mv;
    private final Model model;

    public ChangeQuestion(Controller controller) {
        this.mv = controller.mv;
        this.model = controller.model;

    }

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
