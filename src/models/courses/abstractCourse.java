package models.courses;

import models.Exercises.Exercise;
import models.Logger;

import java.util.ArrayList;
import java.util.List;

abstract class abstractCourse implements Course{
    private final String name;
    private List<Exercise> exercises;
    private int courseRank;

    private final Logger logger = Logger.getInstance();

    public abstractCourse(String name, int courseRank, List<Exercise> exercises) {
        this.name = name;
        this.courseRank = courseRank;
        this.exercises = exercises;

        logger.logChange(name + " course created and exercises have been loaded!");
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
