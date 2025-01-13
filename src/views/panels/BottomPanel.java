package views.panels;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {
    private final JButton spanishButton;
    private final JButton frenchButton;
    private final JButton englishButton;
    private final JButton premiumButton;

    public BottomPanel() {
        setLayout(new BorderLayout());
        //  (Horizontal)
        JPanel languagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        languagePanel. setBackground(new Color(57,81,120));
        spanishButton = new JButton("Spanish");
        frenchButton = new JButton("French");
        englishButton = new JButton("English");

        languagePanel.add(spanishButton);
        languagePanel.add(frenchButton);
        languagePanel.add(englishButton);
        //padding
        languagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // (Vertical)
        JPanel actionPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        actionPanel. setBackground(new Color(57,81,120));
        premiumButton = new JButton("Premium");
        actionPanel.add(premiumButton);
        //padding
        actionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton[] buttons = {spanishButton,frenchButton,englishButton,premiumButton};
        for(JButton button : buttons){
            button.setBackground(new Color(235, 231, 127));
            button.setFont(new Font("Verdana", Font.BOLD,12));
            button.setPreferredSize(new Dimension(100, 40));
        }


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


}

