package views;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    private final JLabel premiumLabel;
    private final JLabel scoreLabel;
    private final JLabel rankLabel;
    private final RankLabelObserver rankLabelObserver;
    private final PremiumLabelObserver premiumLabelObserver;

    public TopPanel( MainView mv) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        premiumLabel = new JLabel("Premium Mode");
        scoreLabel = new JLabel("Rank score : 0");
        rankLabel = new JLabel("Rank : None");
        add(premiumLabel);
        add(rankLabel);
        add(scoreLabel);
        rankLabelObserver = new RankLabelObserver(rankLabel, mv);
        premiumLabelObserver = new PremiumLabelObserver(premiumLabel);
    }

    public JLabel getPremiumLabel() {
        return premiumLabel;
    }

    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    public JLabel getRankLabel() {
        return rankLabel;
    }

    public void updateRank(String rank) {
        rankLabel.setText("Rank: " + rank);
        repaint();
        revalidate();
    }

    public void updateScore(int score) {
        scoreLabel.setText("Rank score: " + score);
        repaint();
        revalidate();
    }

    public void setPremiumLabel(String text) {
        premiumLabel.setText(text);
        repaint();
        revalidate();
    }

    public RankLabelObserver getRankLabelObserver() {
        return rankLabelObserver;
    }

    public PremiumLabelObserver getPremiumLabelObserver() {
        return premiumLabelObserver;
    }
}


