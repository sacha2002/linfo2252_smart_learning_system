package models.Exercises;

import models.Rank;

public class GrammarExercise extends Exercise {
    public GrammarExercise(String text, Rank rank,String answer, String hint) {
        super(text, rank,answer,hint);
    }

    @Override
    public void ask() {
        System.out.println("Grammar Question: " + text);
    }
}
