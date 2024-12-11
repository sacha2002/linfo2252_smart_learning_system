package models.Exercises;

import models.Logger;

import java.util.Objects;

public class Exercise {


    protected String text;
    protected Rank rank;
    protected String answer;
    protected String hint;


    public Exercise(String text, Rank rank, String answer, String hint) {
        this.text = text;
        this.rank = rank;
        this.answer = answer;
        this.hint = hint;
    }

    public String getHint() {
        return hint;
    }


    public String getText() {
        return text;
    }


    public Rank getRank() {
        return rank;
    }

    public boolean checkUserAnswer(String userAnswer) {

        return answer.equalsIgnoreCase(userAnswer);
    }


    @Override
    public String toString() {
        return "Exercise{"  +
                "rank=" + rank +
                ", text='" + text + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return rank == exercise.rank && Objects.equals(answer, exercise.answer) && Objects.equals(hint, exercise.hint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, answer, hint);
    }

    //for children
    public void ask(){

    }
}
