package models.courses;

import models.Exercises.Exercise;

import java.util.ArrayList;
import java.util.List;

abstract class abstractCourse implements Course{
    private final String name;
    private List<Exercise> exercises = new ArrayList<>();
    private int courseRank;

    public abstractCourse(String name, int courseRank, List<Exercise> exercises) {
        this.name = name;
        this.courseRank = courseRank;
        this.exercises = exercises;
    }



    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCourseRank() {
        return courseRank;
    }

    public void setCourseRank(int courseRank) {
        this.courseRank = courseRank;
    }

    @Override
    public List<Exercise> getExercises() {
        return exercises;
    }
}
