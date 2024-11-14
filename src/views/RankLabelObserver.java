package views;

import models.Observer;

import javax.swing.*;

public class RankLabelObserver implements Observer {
    private JLabel rankLabel;

    public RankLabelObserver(JLabel rankLabel) {
        this.rankLabel = rankLabel;
    }

    @Override
    public void update(String message) {
        rankLabel.setText("Rank: " + message);
    }
}

