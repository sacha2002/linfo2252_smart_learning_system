package models.Exercises;

import models.Rank;

public class Exercise {
    protected String text;
    protected Rank rank;

    public Exercise(String text, Rank rank) {
        this.text = text;
        this.rank = rank;
    }

    public String getText() {
        return text;
    }

    public Rank getRank() {
        return rank;
    }

    public void ask(){

    }
}
