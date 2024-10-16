package models.Exercises;

import models.Logger;
import models.Rank;

public class Exercise {

    protected final Logger logger = Logger.getInstance();

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
        logger.logChange("used hint!");
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
        boolean correct = answer.equals(userAnswer);

        logger.logChange(correct? "correct answer!" : "wrong answer!");
        return correct;
    }

    public void ask(){

    }
}
