package views;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import models.Exercises.Exercise;

public class MiddlePanel extends JPanel {
    private final JTextField answerField;
    private final JButton previousQuestionButton;
    private final JButton nextQuestionButton;
    private final JButton hintButton;
    private final List<JLabel> exercises = new ArrayList<>();
    private int currentExerciseIndex = 0;

    public MiddlePanel() {
        setLayout(null); // Using absolute layout for flexibility
        answerField = new JTextField(20);
        answerField.setBounds(150, 250, 300, 30);

        previousQuestionButton = new JButton("<");
        previousQuestionButton.setBounds(100, 250, 45, 30);

        nextQuestionButton = new JButton(">");
        nextQuestionButton.setBounds(455, 250, 45, 30);

        hintButton = new JButton("?");
        hintButton.setBounds(500, 250, 45, 30);

        add(answerField);
        add(previousQuestionButton);
        add(nextQuestionButton);
        add(hintButton);
    }

    public JTextField getAnswerField() {
        return answerField;
    }

    public JButton getPreviousQuestionButton() {
        return previousQuestionButton;
    }

    public JButton getNextQuestionButton() {
        return nextQuestionButton;
    }

    public JButton getHintButton() {
        return hintButton;
    }

    public void displayExercise(Exercise exercise, int index) {
        clearLabels();
        JLabel label = new JLabel(exercise.getText(), JLabel.CENTER);
        label.setBounds(0, 150, 600, 30);
        add(label);
        exercises.add(label);
        currentExerciseIndex = index;
        repaint();
        revalidate();
        answerField.requestFocusInWindow();
    }


    public void clearLabels() {
        for (JLabel label : exercises) {
            remove(label);
        }
        exercises.clear();
    }

    public int getCurrentExerciseIndex() {
        return currentExerciseIndex;
    }
}

