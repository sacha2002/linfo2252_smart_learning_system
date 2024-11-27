package views;

import models.Exercises.Exercise;
import views.panels.BottomPanel;
import views.panels.MiddlePanel;
import views.panels.TopPanel;
import views.observers.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView {

    private final JFrame frame;
    private final TopPanel topPanel;
    private final MiddlePanel middlePanel;
    private final BottomPanel bottomPanel;

    private String course;

    public MainView() {
        // Main frame setup
        frame = new JFrame("LEARNING APP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());

        // Panels
        topPanel = new TopPanel(this);
        middlePanel = new MiddlePanel();
        bottomPanel = new BottomPanel();

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Finalize frame setup
        frame.setFocusable(true);
        frame.setVisible(true);
    }

    public void addCourseButtonListener(ActionListener listener) {
        bottomPanel.getSpanishButton().addActionListener(listener);
        bottomPanel.getFrenchButton().addActionListener(listener);
        bottomPanel.getEnglishButton().addActionListener(listener);
    }

    public void addChangeQuestionButtonListener(ActionListener listener) {
        middlePanel.getNextQuestionButton().addActionListener(listener);
        middlePanel.getPreviousQuestionButton().addActionListener(listener);
    }

    public void addHintButtonListener(ActionListener listener) {
        middlePanel.getHintButton().addActionListener(listener);
    }

    public void addPremiumButtonListener(ActionListener listener) {
        bottomPanel.getPremiumButton().addActionListener(listener);
    }

    public void addTextfieldListener(ActionListener listener) {
        middlePanel.getAnswerField().addActionListener(listener);
    }

    public void addActivateCourseButtonListener(ActionListener listener) {
        bottomPanel.getActivateCourseButton().addActionListener(listener);
    }

    public void displayExercise(Exercise exercise, int index) {
        middlePanel.displayExercise(exercise, index);
    }


    public void displayHint(String hint) {
        middlePanel.updateHint(hint);
    }

    public void setHintTrue(){
        middlePanel.setHintTrueFalse();
    }


    public void closeWindow() {
        frame.dispose();
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void displayMessageCorrectness(String message, boolean correct) {
        middlePanel.displayMessage(message, correct);
    }

    public int getCurrentExerciseIndex(){
        return middlePanel.getCurrentExerciseIndex();
    }

    public JTextField getAnswerField(){
        return middlePanel.getAnswerField();
    }

    public  RankLabelObserver getRankLabelObserver(){
        return topPanel.getRankLabelObserver();
    }

    public  PremiumLabelObserver getPremiumLabelObserver(){
        return topPanel.getPremiumLabelObserver();
    }

    public  StreakLabelObserver getStreakLabelObserver(){
        return topPanel.getStreakLabelObserver();
    }

    public  ScoreLabelObserver getScoreLabelObserver(){
        return topPanel.getScoreLabelObserver();
    }

    public JButton getPremiumButton(){
        return bottomPanel.getPremiumButton();
    }

    public JButton getSpanishButton(){
        return bottomPanel.getSpanishButton();
    }

    public JButton getEnglishButton(){
        return bottomPanel.getEnglishButton();
    }
    public JButton getFrenchButton(){
        return bottomPanel.getFrenchButton();
    }
    public JButton getActivateCourseButton(){
        return bottomPanel.getActivateCourseButton();
    }
    public JButton getPreviousQuestionButton(){
        return middlePanel.getPreviousQuestionButton();
    }
    public JButton getNextQuestionButton(){
        return middlePanel.getNextQuestionButton();
    }



}
