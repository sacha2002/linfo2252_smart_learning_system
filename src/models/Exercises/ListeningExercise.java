package models.Exercises;

import models.Rank;

public class ListeningExercise extends Exercise {
    private String audioFilePath;

    public ListeningExercise(String text, Rank rank, String answer, String hint, String audioFilePath) {
        super(text, rank, answer, hint);
        this.audioFilePath = audioFilePath;
    }

    public String getAudioFilePath() {
        return audioFilePath; //dont really know if this is this way
    }

    @Override
    public void ask() {
        System.out.println("Listening Question: " + text + " (Audio: " + audioFilePath + ")");
    }
}
