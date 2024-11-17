package models.courses;

import models.Exercises.Exercise;
import models.Observer;

import java.util.List;

public interface Course {
    String getName();
    int getCourseRank();
    List<Exercise> getExercises();
    String toString();
    int getId();
    boolean practice( Exercise exercise,String userAnswer);
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    int getExcerciseIndex(Exercise exercise);
}
