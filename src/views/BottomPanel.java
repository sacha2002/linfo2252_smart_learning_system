package views;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {
    private final JButton spanishButton;
    private final JButton frenchButton;
    private final JButton englishButton;
    private final JButton premiumButton;
    private final JButton activateCourseButton;

    public BottomPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        spanishButton = new JButton("Spanish");
        frenchButton = new JButton("French");
        englishButton = new JButton("English");
        premiumButton = new JButton("Premium");
        activateCourseButton = new JButton("Activate Course");

        add(spanishButton);
        add(frenchButton);
        add(englishButton);
        add(premiumButton);
        add(activateCourseButton);
    }

    public JButton getSpanishButton() {
        return spanishButton;
    }

    public JButton getFrenchButton() {
        return frenchButton;
    }

    public JButton getEnglishButton() {
        return englishButton;
    }

    public JButton getPremiumButton() {
        return premiumButton;
    }

    public JButton getActivateCourseButton() {
        return activateCourseButton;
    }
}

