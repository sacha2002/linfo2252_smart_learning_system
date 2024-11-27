package views.observers;

import models.Exercises.Exercise;

import javax.swing.*;

public class StreakLabelObserver implements Observer {
    private final JLabel streakLabel;
    private final JPanel panel;

    public StreakLabelObserver(JLabel streakLabel , JPanel panel) {
        this.streakLabel = streakLabel;
        this.panel = panel;
    }

    @Override
    public void update(String message) {
        streakLabel.setText("Streak: "+ message);
        panel.repaint();
        panel.revalidate();
    }

    @Override
    public void update(Exercise message, int index) {
        //not used here
    }
}
