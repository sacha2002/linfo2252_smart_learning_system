package models.courses;

import models.Exercises.Exercise;
import models.Exercises.ExerciseData;
import models.Logger;
import models.Observer;
import models.Rank;

import java.util.ArrayList;
import java.util.List;

abstract class abstractCourse implements Course{
    private static int idCounter = 0;


    private final String name;
    private List<Exercise> exercises;
    private int courseRank;
    private static int id;
    private final List<Observer> observers = new ArrayList<>();

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
           checkRankPromotion();
           logger.logChange(name + " course score increased to" + courseRank,name+id,this.toString());
           return true;
       }
       return false;
    }

    private void checkRankPromotion(){
        if(courseRank > exercises.getFirst().getRank().getUpperBound()){
            for (Rank rank : Rank.values()) {
                if(courseRank < rank.getUpperBound() && courseRank>rank.getLowerBound()){
                    exercises = ExerciseData.getExerciseLookup().getOrDefault(name+rank.getName(),new ArrayList<>());
                    notifyObservers(rank.getName());
                    logger.logChange(name + " rank promotion! to " + rank.getName(),name+id,this.toString());
                }
            }
        }
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

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
