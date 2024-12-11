package models.courses;

import models.Exercises.Exercise;
import views.observers.Observer;

import java.util.List;

public interface Course {
    String getName();
    List<Exercise> getExercises();
    String toString();
    int getExcerciseIndex(Exercise exercise);
    boolean practice( Exercise exercise,String userAnswer);
    void addRankObserver(Observer observer) ;
    void addScoreObserver(Observer observer);

}
