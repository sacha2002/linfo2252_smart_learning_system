package views.observers;

import models.Exercises.Exercise;
import views.MainView;

import javax.swing.*;

public class RankLabelObserver implements Observer {
    private final JLabel rankLabel;
    private final JPanel panel;
    private final MainView mv;

    public RankLabelObserver(JLabel rankLabel,MainView mv,JPanel panel) {
        this.rankLabel = rankLabel;
        this.mv = mv;
        this.panel = panel;
    }

    @Override
    public void update(String message) {
        rankLabel.setText("Rank: " + message);
        panel.repaint();
        panel.revalidate();
    }

    @Override
    public void update(Exercise message, int index) {
        mv.displayExercise(message,index);
    }
}

