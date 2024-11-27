package views.observers;

import models.Exercises.Exercise;

import javax.swing.*;

public class PremiumLabelObserver implements Observer {
    private final JLabel premiumLabel;
    private final JPanel panel;

    public PremiumLabelObserver(JLabel premiumLabel, JPanel panel) {
        this.premiumLabel = premiumLabel;
        this.panel = panel;
    }

    @Override
    public void update(String message) {
        premiumLabel.setText( message);
        panel.repaint();
        panel.revalidate();
    }

    @Override
    public void update(Exercise message, int index) {

    }
}
