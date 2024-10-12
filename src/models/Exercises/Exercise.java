package models.Exercises;

import models.Rank;

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

    public String getAnswer() {
        return answer;
    }

    public String getText() {
        return text;
    }

    public Rank getRank() {
        return rank;
    }

    public boolean checkUserAnswer(String userAnswer) {
        return answer.equals(userAnswer);
    }

    public void ask(){

    }
}
