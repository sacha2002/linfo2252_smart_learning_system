package models.courses;

import models.Exercises.Exercise;
import models.Exercises.ExerciseData;
import models.Logger;
import views.observers.Observer;
import models.Exercises.Rank;
import views.observers.ScoreLabelObserver;

import java.util.ArrayList;
import java.util.List;

abstract class abstractCourse implements Course{
    private static int idCounter = 0;


    private final String name;
    private List<Exercise> exercises;
    private int courseRank;
    private static int id;
    private final List<Observer> rankObservers = new ArrayList<>();
    private final List<Observer> scoreObservers = new ArrayList<>();

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


    @Override
    public boolean practice( Exercise exercise ,String userAnswer){
       boolean correct = exercise.checkUserAnswer(userAnswer);
       if(correct){
           courseRank++;
           notifyScoreObservers(Integer.toString(courseRank));
           checkRankPromotion();
           logger.logChange(name + " course score increased to " + courseRank,name+id,this.toString());
           return true;
       }
       return false;
    }

    private void checkRankPromotion(){
        if(courseRank > exercises.getFirst().getRank().getUpperBound()){
            for (Rank rank : Rank.values()) {
                if(courseRank <= rank.getUpperBound() && courseRank >= rank.getLowerBound()){
                    exercises = ExerciseData.getExerciseLookup().getOrDefault(name+rank.getName(),new ArrayList<>());
                    System.out.println(exercises.getFirst());
                    notifyRankObservers(rank.getName(),exercises.getFirst());
                    logger.logChange(name + " rank promotion! to " + rank.getName(),name+id,this.toString());
                }
            }
        }
    }

    @Override
    public int getExcerciseIndex(Exercise exercise){
        return exercises.indexOf(exercise);
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



    public void addRankObserver(Observer observer) {
        rankObservers.add(observer);
        notifyRankObservers(Rank.getRankByNumber(courseRank).getName(),null);
    }


    private void notifyRankObservers(String message,Exercise exercise) {
        for (Observer observer : rankObservers) {
                observer.update(message);
                if( exercise != null) {
                    System.out.println(exercise.getText());
                    observer.update(exercise ,0);
                }
        }
    }

    public void addScoreObserver(Observer observer) {
        scoreObservers.add(observer);
        notifyScoreObservers(String.valueOf(courseRank));
    }

    private void notifyScoreObservers(String message) {
        for (Observer observer : scoreObservers) {
            observer.update(message);
        }
    }
}
