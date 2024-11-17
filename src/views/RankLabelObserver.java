package views;

import models.Exercises.Exercise;
import models.Observer;

import javax.swing.*;

public class RankLabelObserver implements Observer {
    private JLabel rankLabel;
    private MainView mv;

    public RankLabelObserver(JLabel rankLabel,MainView mv) {
        this.rankLabel = rankLabel;
        this.mv = mv;
    }

    @Override
    public void update(String message) {
        rankLabel.setText("Rank: " + message);
    }

    @Override
    public void update(Exercise message, int index) {
        mv.displayExercise(message,index);
    }
}

