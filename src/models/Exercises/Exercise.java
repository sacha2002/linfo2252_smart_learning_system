package models.Exercises;

import models.Logger;
import models.Rank;

import java.util.Objects;

public class Exercise {

    protected final Logger logger = Logger.getInstance();
    private static int idCounter = 0;

    protected String text;
    protected Rank rank;
    protected String answer;
    protected String hint;

    protected int id; //single identifier for logs

    public Exercise(String text, Rank rank, String answer, String hint) {
        this.text = text;
        this.rank = rank;
        this.answer = answer;
        this.hint = hint;
        this.id = idCounter;
        idCounter++;
    }

    public String getHint() {
        logger.logChange("used hint!",text+id,this.toString());
        return hint;
    }

    public String getAnswer() {
        return answer;
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

    public Rank getRank() {
        return rank;
    }

    public boolean checkUserAnswer(String userAnswer) {
        boolean correct = answer.equalsIgnoreCase(userAnswer);

        logger.logChange(correct? "correct answer!" : "wrong answer!",text+id,this.toString());
        return correct;
    }


    @Override
    public String toString() {
        return "Exercise{"  + "id="+ id +
                "rank=" + rank +
                ", text='" + text + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return id == exercise.id && rank == exercise.rank && Objects.equals(answer, exercise.answer) && Objects.equals(hint, exercise.hint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, answer, hint, id);
    }

    //for children
    public void ask(){

    }
}
