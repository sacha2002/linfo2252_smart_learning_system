package views.panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import models.Exercises.Exercise;

public class MiddlePanel extends JPanel {
    private final JTextField answerField;
    private final JButton previousQuestionButton;
    private final JButton nextQuestionButton;
    private final JButton hintButton;
    private final List<JLabel> exercises = new ArrayList<>();
    private JLabel hintLabel;
    private JLabel messageLabel;
    private int currentExerciseIndex = 0;
    private boolean hintIsActivated = false;

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

        hintLabel = new JLabel("", JLabel.CENTER);
        hintLabel.setBounds(0, 300, 600, 30);

        messageLabel = new JLabel("", JLabel.CENTER);
        messageLabel.setBounds(0, 200, 600, 30);

        JButton[] buttons = {previousQuestionButton,nextQuestionButton,hintButton};
        for(JButton button : buttons){
            button.setBackground(new Color(235, 231, 127));
            button.setFont(new Font("Verdana", Font.BOLD,12));
        }

        add(answerField);
        add(previousQuestionButton);
        add(nextQuestionButton);
        add(hintButton);
        add(hintLabel);
        add(messageLabel);
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

    public void removeExercise(){
        clearLabels();
        repaint();
        revalidate();
    }



    public void displayExercise(Exercise exercise, int index) {
        clearLabels();
        updateHint("");
        if (exercise != null){
            JLabel label = new JLabel(exercise.getText(), JLabel.CENTER);
            label.setBounds(0, 150, 600, 30);
            label.setFont(new Font("Verdana", Font.BOLD,12));
            add(label);
            exercises.add(label);
            currentExerciseIndex = index;
            repaint();
            revalidate();
            answerField.requestFocusInWindow();
        }
        repaint();
        revalidate();
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

    public void setHintTrueFalse() {
        hintIsActivated = !hintIsActivated;
    }

    public void toggleHintButton(boolean active){
       hintButton.setEnabled(active);
        if(!active){
            updateHint("");
        }
    }


    public void updateHint(String hint) {
        if (hint.equals(""))
            hintIsActivated = false;
        if (hintIsActivated) {
            hintLabel.setText("hint : " + hint);

        }
        else {
            hintLabel.setText("");
        }
        repaint();
        revalidate();
    }

    public void displayMessage(String message, boolean correct) {
        if (correct)
            messageLabel.setForeground(java.awt.Color.GREEN);
        else messageLabel.setForeground(java.awt.Color.RED);
        messageLabel.setText(message);
        repaint();
        revalidate();
        Timer timer = new Timer(3000, e -> {
            messageLabel.setText("");
            repaint();
            revalidate();
        });
        timer.setRepeats(false);
        timer.start();
    }
}

