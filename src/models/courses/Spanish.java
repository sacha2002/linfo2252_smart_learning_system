package models.courses;

import models.Exercises.Exercise;

import java.util.List;

public class Spanish extends abstractCourse {
    public Spanish(int courseRank, List<Exercise> exercises) {
        super("SPANISH", courseRank, exercises);
    }
}
