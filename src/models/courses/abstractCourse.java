package models.courses;

import models.Exercises.Exercise;
import models.Logger;

import java.util.List;

abstract class abstractCourse implements Course{
    private static int idCounter = 0;


    private final String name;
    private List<Exercise> exercises;
    private int courseRank;
    private static int id;

    private final Logger logger = Logger.getInstance();

    public abstractCourse(String name, int courseRank, List<Exercise> exercises) {
        this.name = name;
        this.courseRank = courseRank;
        this.exercises = exercises;
        this.id=idCounter;
        idCounter++;

        logger.logChange(name + " course created and exercises have been loaded!",name+id,this.toString());
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCourseRank() {
        return courseRank;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setCourseRank(int courseRank) {
        this.courseRank = courseRank;
        logger.logChange(name + " course rank have been changed",name+id,this.toString());
    }

    @Override
    public boolean practice( Exercise exercise ,String userAnswer){
       boolean correct = exercise.checkUserAnswer(userAnswer);
       if(correct){
           courseRank++;
           logger.logChange(name + " course rank has ",name+id,this.toString());
           return true;
       }
       return false;
    }

    @Override
    public List<Exercise> getExercises() {
        return exercises;
    }

    @Override
    public String toString() {
        return "abstractCourse{" + "id="+ id +
                "name='" + name + '\'' +
                ", exercises=" + exercises +
                ", courseRank=" + courseRank +
                ", logger=" + logger +
                '}';
    }
}
