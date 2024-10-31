package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import models.Exercises.Exercise;

public class MainView {

    private JFrame frame;
    private JTextField answerField;
    private JLabel premiumLabel;
    private JLabel scoreLabel;
    private JButton premiumButton;
    private JButton spanishButton;
    private JButton frenchButton;
    private JButton englishButton;
    private JButton nextQuestionButton;
    private JButton previousQuestionButton;
    private JLabel questionLabel;
    private List<JLabel> exercises = new ArrayList<>();
    private List<Exercise> currentExercises = new ArrayList<>();
    private int currentExerciseIndex = 0;


    public MainView() {
        // Configuration de la fenêtre principale
        frame = new JFrame("LEARNING APP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(null); // Positionnement absolu

        // Labels en haut
        premiumLabel = new JLabel("Free Mode");
        premiumLabel.setBounds(10, 10, 140, 30); // X=10, Y=10, Largeur=100, Hauteur=30
        frame.add(premiumLabel);

        scoreLabel = new JLabel("Score :");
        scoreLabel.setBounds(490, 10, 100, 30); // X=490, Y=10, Largeur=100, Hauteur=30
        frame.add(scoreLabel);


        answerField = new JTextField(20);
        answerField.setBounds(150, 250, 300, 30); // X=150, Y=250, Largeur=300, Hauteur=30
        frame.add(answerField);

        // Boutons < et > de chaque côté du champ de réponse
        previousQuestionButton = new JButton("<");
        previousQuestionButton.setBounds(100, 250, 45, 30); // À gauche de answerField
        frame.add(previousQuestionButton);

        nextQuestionButton = new JButton(">");
        nextQuestionButton.setBounds(455, 250, 45, 30); // À droite de answerField
        frame.add(nextQuestionButton);

        // Panel pour les boutons de langue et Premium en bas
        spanishButton = new JButton("Spanish");
        spanishButton.setBounds(50, 500, 100, 30); // Position en bas à gauche
        frame.add(spanishButton);

        frenchButton = new JButton("French");
        frenchButton.setBounds(175, 500, 100, 30);
        frame.add(frenchButton);

        englishButton = new JButton("English");
        englishButton.setBounds(300, 500, 100, 30);
        frame.add(englishButton);

        premiumButton = new JButton("Premium");
        premiumButton.setBounds(425, 500, 100, 30); // Position en bas à droite
        premiumButton.addActionListener(e -> togglePremiumMode());
        frame.add(premiumButton);

        // Écouteur de touches pour la navigation
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    showPreviousExercise();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    showNextExercise();
                }
            }
        });
        frame.setFocusable(true);

        // Afficher la fenêtre
        frame.setVisible(true);
    }

    public void displayExercise(String question) {
        this.clearLabels();
        // Label de la question au centre de la fenêtre
        JLabel label1 = new JLabel(question, JLabel.CENTER);
        label1.setBounds(0, 150, 600, 30); // Position : X=150, Y=150, largeur=300, hauteur=30
        frame.add(label1);

        // Champ de réponse sous la question
        answerField.setBounds(150, 250, 300, 30); // Position : X=150, Y=200
        frame.add(answerField);

        exercises.add(label1);

        frame.repaint();
        frame.revalidate();
        answerField.requestFocusInWindow();
    }


    public void addButtonsLister(ActionListener listener) {
        spanishButton.addActionListener(listener);
        frenchButton.addActionListener(listener);
        englishButton.addActionListener(listener);
    }

    public void addTextfieldListener(ActionListener listener){
        answerField.addActionListener(listener);
    }

    public JButton getEnglishButton(){
        return englishButton;
    }
    public JButton getFrenchButton(){
        return frenchButton;
    }
    public JButton getSpanishButton(){
        return spanishButton;
    }
    public JTextField getAnswerField(){
        return answerField;
    }

    private void showPreviousExercise() {
        if (currentExerciseIndex > 0) {
            currentExerciseIndex--;
            displayExercise(currentExercises.get(currentExerciseIndex).getText());
        }
    }

    private void showNextExercise() {
        if (currentExerciseIndex < currentExercises.size() - 1) {
            currentExerciseIndex++;
            displayExercise(currentExercises.get(currentExerciseIndex).getText());
        }
    }



    private void togglePremiumMode() {
        if (premiumButton.getText().equals("premium")) {
            premiumLabel.setText("premium mode");
            premiumButton.setText("free");
        } else {
            premiumLabel.setText("free mode");
            premiumButton.setText("premium");
        }
        frame.repaint();
        frame.revalidate();
    }

    public void clearLabels() {
        for (JLabel label : exercises) {
            frame.remove(label);
        }
    }

    public void closeWindow() {
        frame.dispose();
    }
}