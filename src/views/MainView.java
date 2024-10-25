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
import models.Exercises.ExerciseData;
import models.courses.Course;
import models.courses.French;
import models.courses.Spanish;

public class MainView {

    private JFrame frame;
    private JPanel centerPanel;
    private BorderLayout borderLayout1;
    private JLabel premiumLabel;
    private JButton premiumButton;
    private JButton spanishButton;
    private JButton frenchButton;
    private JButton englishButton;
    private List<JLabel> exercises = new ArrayList<>();
    private List<Exercise> currentExercises = new ArrayList<>();
    private int currentExerciseIndex = 0;

    public MainView() {
        this.frame = new JFrame("LEARNING APP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout()); // Utiliser BorderLayout pour le frame

        // Créer un panel principal avec BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Créer le topPanel pour le titre et le premiumLabel
        JPanel topPanel = new JPanel(new BorderLayout());
        premiumLabel = new JLabel("Free Mode", JLabel.LEFT);
        topPanel.add(premiumLabel, BorderLayout.WEST); // Placer premiumLabel à gauche

        // Créer le panneau central pour ajouter des composants dynamiques
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // Empiler les labels verticalement

        // Créer le panneau des boutons de cours
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        spanishButton = new JButton("Spanish");
        frenchButton = new JButton("French");
        englishButton = new JButton("English");

        spanishButton.addActionListener(e -> loadCourse("spanish"));
        frenchButton.addActionListener(e -> loadCourse("french"));
        englishButton.addActionListener(e -> loadCourse("english"));

        buttonPanel.add(spanishButton);
        buttonPanel.add(frenchButton);
        buttonPanel.add(englishButton);

        // Ajouter le bouton premium au bas du panel des boutons
        premiumButton = new JButton("Premium");
        premiumButton.setPreferredSize(new Dimension(100, 26));
        premiumButton.addActionListener(e -> togglePremiumMode());
        buttonPanel.add(premiumButton);

        // Ajouter les panneaux au panneau principal
        mainPanel.add(topPanel, BorderLayout.NORTH); // Placer le topPanel en haut
        mainPanel.add(centerPanel, BorderLayout.CENTER); // Placer le centerPanel au centre
        mainPanel.add(buttonPanel, BorderLayout.SOUTH); // Placer le buttonPanel en bas

        // Ajouter le panneau principal à la fenêtre
        frame.add(mainPanel);
        frame.setVisible(true); // Afficher la fenêtre



        // Add key listener for arrow keys
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
    }

    private void loadCourse(String courseName) {

    }

    public void addButtonListeners(ActionListener listener) {
        spanishButton.addActionListener(listener);
        frenchButton.addActionListener(listener);
        englishButton.addActionListener(listener);
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

    public void displayExercise(String question) {
        this.clearLabels();
        JLabel label1 = new JLabel(question, JLabel.CENTER);
        centerPanel.setLayout(new GridBagLayout()); // Utiliser GridBagLayout pour centrer le label

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Centrer le label
        gbc.insets = new Insets(10, 10, 10, 10); // Optionnel : ajouter des marges

        centerPanel.add(label1, gbc); // Ajouter le label avec les contraintes spécifiées
        exercises.add(label1);

        frame.repaint();
        frame.revalidate();

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
            centerPanel.remove(label);
        }
    }

    public void closeWindow() {
        frame.dispose();
    }
}