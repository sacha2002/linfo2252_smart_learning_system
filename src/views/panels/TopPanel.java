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
            setLayout(new BorderLayout());


            JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            premiumLabel = new JLabel("Premium Mode", SwingConstants.LEFT);
            streakLabel = new JLabel("Streak: ",SwingConstants.LEFT);
            leftPanel.add(premiumLabel);
            leftPanel.add(streakLabel);

            JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            rankLabel = new JLabel("Rank : None");
            scoreLabel = new JLabel("Rank score : 0");
            rightPanel.add(rankLabel);
            rightPanel.add(scoreLabel);

            // Add panels to the main panel
            add(leftPanel, BorderLayout.WEST);
            add(rightPanel, BorderLayout.EAST);

            // Initialize observers
            streakLabelObserver = new StreakLabelObserver(streakLabel,this);
            rankLabelObserver = new RankLabelObserver(rankLabel, mv,this);
            premiumLabelObserver = new PremiumLabelObserver(premiumLabel,this);
            scoreLabelObserver = new ScoreLabelObserver(scoreLabel,this);

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


