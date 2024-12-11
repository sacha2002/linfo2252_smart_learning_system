package models.courses;

import models.Exercises.Exercise;

import java.util.List;

public class English extends abstractCourse{
    public English(int courseRank, List<Exercise> exercises) {
        super("ENGLISH", courseRank, exercises);
    }
}
