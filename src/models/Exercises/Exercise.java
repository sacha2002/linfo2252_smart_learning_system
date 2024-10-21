package models.Exercises;

import models.Logger;
import models.Rank;

public class Exercise {

    protected final Logger logger = Logger.getInstance();
    private static int idCounter = 0;

    protected String text;
    protected Rank rank;
    protected String answer;
    protected String hint;
    protected int id;

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
        boolean correct = answer.equals(userAnswer);

        logger.logChange(correct? "correct answer!" : "wrong answer!",text+id,this.toString());
        return correct;
    }

    public void ask(){

    }

    @Override
    public String toString() {
        return "Exercise{"  + "id="+ id +
                "rank=" + rank +
                ", text='" + text + '\'' +
                '}';
    }
}
