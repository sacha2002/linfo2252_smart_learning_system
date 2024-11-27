package views.observers;

import models.Exercises.Exercise;

import javax.swing.*;

public class ScoreLabelObserver implements Observer {
    private final JLabel scoreLabel;
    private final JPanel panel;

    public ScoreLabelObserver(JLabel scoreLabel , JPanel panel) {
        this.scoreLabel = scoreLabel;
        this.panel = panel;
    }

    @Override
    public void update(String message) {
        scoreLabel.setText("Score: "+ message);
        panel.repaint();
        panel.revalidate();
    }

    @Override
    public void update(Exercise message, int index) {
        //not used here
    }
}
