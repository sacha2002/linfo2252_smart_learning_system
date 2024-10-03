package models.courses;

import models.Exercises.Exercise;

import java.util.List;

public interface Course {
    String getName();
    int getCourseRank();
    List<Exercise> getExercises();
}
