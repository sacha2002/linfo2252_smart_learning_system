package views.panels;

import views.MainView;
import views.observers.PremiumLabelObserver;
import views.observers.RankLabelObserver;
import views.observers.ScoreLabelObserver;
import views.observers.StreakLabelObserver;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    private final JLabel premiumLabel;
    private final JLabel scoreLabel;
    private final JLabel rankLabel;
    private final JLabel streakLabel;

    private final StreakLabelObserver streakLabelObserver;
    private final RankLabelObserver rankLabelObserver;
    private final PremiumLabelObserver premiumLabelObserver;
    private final ScoreLabelObserver scoreLabelObserver;

    public TopPanel(MainView mv) {

        setBackground(new Color(57, 81, 120));
        setLayout(new BorderLayout());

        // Left Panel for Premium and Streak
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(57, 81, 120));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); // Vertical layout
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        premiumLabel = new JLabel("Premium Mode", SwingConstants.LEFT);
        streakLabel = new JLabel("Streak: ", SwingConstants.LEFT);

        // Align the labels to the left within the BoxLayout
        premiumLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        streakLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        leftPanel.add(premiumLabel);
        leftPanel.add(Box.createVerticalStrut(5)); // Add some vertical spacing
        leftPanel.add(streakLabel);

        // Right Panel for Rank and Score
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(57, 81, 120));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS)); // Vertical layout
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        rankLabel = new JLabel("Rank : None", SwingConstants.RIGHT);
        scoreLabel = new JLabel("Rank score : 0", SwingConstants.RIGHT);

        // Align the labels to the right within the BoxLayout
        rankLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        scoreLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        rightPanel.add(rankLabel);
        rightPanel.add(Box.createVerticalStrut(5)); // Add some vertical spacing
        rightPanel.add(scoreLabel);

        // Set styles for all labels
        JLabel[] labels = {premiumLabel, scoreLabel, rankLabel, streakLabel};
        for (JLabel label : labels) {
            label.setForeground(Color.WHITE); // Text color
            label.setFont(new Font("Verdana", Font.BOLD, 12));
        }

        // Add panels to the main panel
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

        // Initialize observers
        streakLabelObserver = new StreakLabelObserver(streakLabel, this);
        rankLabelObserver = new RankLabelObserver(rankLabel, mv, this);
        premiumLabelObserver = new PremiumLabelObserver(premiumLabel, this);
        scoreLabelObserver = new ScoreLabelObserver(scoreLabel, this);
    }






    public RankLabelObserver getRankLabelObserver() {
        return rankLabelObserver;
    }

    public PremiumLabelObserver getPremiumLabelObserver() {
        return premiumLabelObserver;
    }

    public StreakLabelObserver getStreakLabelObserver() {
        return streakLabelObserver;
    }

    public ScoreLabelObserver getScoreLabelObserver() {
        return scoreLabelObserver;
    }
}


