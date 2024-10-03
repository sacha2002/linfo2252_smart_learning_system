package models.Exercises;

import models.Rank;

public class GrammarExercise extends Exercise {
    public GrammarExercise(String text, Rank rank) {
        super(text, rank);
    }

    @Override
    public void ask() {
        System.out.println("Grammar Question: " + text);
    }
}