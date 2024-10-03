package models.courses;

import models.Exercises.Exercise;

import java.util.List;

public class French extends abstractCourse {
    public French(int courseRank, List<Exercise> exercises) {
        super("french", courseRank, exercises);
    }
}
