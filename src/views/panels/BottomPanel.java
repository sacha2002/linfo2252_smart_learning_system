package views.panels;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {
    private final JButton spanishButton;
    private final JButton frenchButton;
    private final JButton englishButton;
    private final JButton premiumButton;
    private final JButton activateCourseButton;

    public BottomPanel() {
        setLayout(new BorderLayout());

        //  (Horizontal)
        JPanel languagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        spanishButton = new JButton("Spanish");
        frenchButton = new JButton("French");
        englishButton = new JButton("English");

        languagePanel.add(spanishButton);
        languagePanel.add(frenchButton);
        languagePanel.add(englishButton);

        // (Vertical)
        JPanel actionPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        premiumButton = new JButton("Premium");
        activateCourseButton = new JButton("Activate Course");

        actionPanel.add(premiumButton);
        actionPanel.add(activateCourseButton);

        //padding
        actionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(languagePanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.EAST);
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

