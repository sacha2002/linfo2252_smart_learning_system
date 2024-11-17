package views;

import models.Exercises.Exercise;
import models.Observer;

import javax.swing.*;

public class PremiumLabelObserver implements Observer {
    private JLabel premiumLabel;

    public PremiumLabelObserver(JLabel premiumLabel) {
        this.premiumLabel = premiumLabel;
    }

    @Override
    public void update(String message) {
        premiumLabel.setText("Free mode: "+ message);
    }

    @Override
    public void update(Exercise message, int index) {

    }
}
