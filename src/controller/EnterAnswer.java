package controller;

import models.Exercises.Exercise;
import models.Model;
import models.courses.Course;
import views.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class EnterAnswer implements ActionListener {

    static String answer = "";

    private final Optional<MainView> mv;
    private final Model model;


    public EnterAnswer(Controller controller) {
        this.mv = controller.mv;
        this.model = controller.model;
    }

    public void actionPerformed(ActionEvent e) {
        mv.ifPresent(mv -> {
            answer = mv.getAnswerField().getText();
            if( !model.practice()){
                mv.displayMessageCorrectness("You can't practice now ! Not enough energy", false);
                return;
            }
            Course selectedCourse = model.getSelectedCourse();
            Exercise currentExercice = selectedCourse.getExercises().get(mv.getCurrentExerciseIndex());
            if (selectedCourse.practice(currentExercice,answer)) {
                mv.displayMessageCorrectness("Correct ! Well done", true);
            }
            else
                mv.displayMessageCorrectness("Incorrect ! Try again", false);
        });
    }
}
