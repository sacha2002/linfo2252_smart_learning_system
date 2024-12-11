package models;

import models.Energy.EnergySystem;
import models.Exercises.Exercise;
import models.Exercises.ExerciseData;
import models.courses.Course;
import models.courses.English;
import models.courses.French;
import models.courses.Spanish;
import models.features.*;


import java.util.*;

public class Model {

    private final Logger logger = Logger.getInstance();

     private final List<Course> coursesList = new ArrayList<>();
     private final List<String> availableCourses =  new ArrayList<>();
     private Course selectedCourse = null;
     private final String username;

     private final Streak streak;

    private final EnergySystem energySystem;
    private boolean isPremium;

    private final Set<FeatureCommand> activeFeatures = new HashSet<>();

    public Model(String username, int streak) {
        this.username = username;
        this.streak = new Streak(streak);
        this.isPremium=false;
        this.energySystem = new EnergySystem();
        //all rank in silver just for demo
        coursesList.add(new Spanish(650, ExerciseData.getSpanishSilverExercises()));
        coursesList.add(new French(650, ExerciseData.getFrenchSilverExercises()));
        coursesList.add(new English(998, ExerciseData.getEnglishSilverExercises()));
        //init features
        activeFeatures.add(new PremiumCommand(this));
        activeFeatures.add(new EnglishCourseFeature(this));
        activeFeatures.add(new FrenchCourseFeature(this));
        activeFeatures.add(new SpanishCourseFeature(this));

        logger.logChange("user: " + username + " has been created", username, this.toString());
    }


    public boolean practice() {
        if (energySystem.canPractice(isPremium)) {
            energySystem.useEnergy(isPremium);
            streak.updateStreak();
            logger.logChange(username + " practiced! Current energy: " + energySystem.getCurrentEnergy(), username, this.toString());
            return true;
        } else {
            logger.logChange(username + " does not have enough energy to practice.", username, this.toString());
            return false;
        }
    }

    private FeatureCommand getCommandByName( String name) {
        return activeFeatures.stream()
                .filter(command -> command.getName().equals(name))
                .findFirst()
                .orElse(null);
    }


    public void activateFeature(String name) {
         FeatureCommand command = getCommandByName(name);
        if (command != null) {
            command.execute();
        }
    }

    public void deactivateFeature(String name) {
        FeatureCommand command = getCommandByName(name);
        if (command != null) {
            command.undo();
        }
    }

    public void updateAvailableCourses( boolean activate,String courseName) {
            if (activate) {
                availableCourses.add(courseName);
                logger.logChange("new questions for "+ courseName + " has appeared in the middle panel", courseName, courseName);
            } else {
                availableCourses.remove(courseName);
                logger.logChange("removed questions for "+ courseName + " from the middle panel", courseName, courseName);
            }
    }

    public List<String> getAvailableCourses(){
        return availableCourses;
    }



    //if it cant practice, no course is given( as second measure)
    public Course getCourse(String courseName){
        if(!energySystem.canPractice(isPremium) && availableCourses.contains(courseName))
            return null;

        for (Course c : coursesList) {
            if(courseName != null && courseName.equals(c.getName())){
                return c;
            }
        }
        return null;
    }

    //if he cant practice, the courses arent available( as second measure)
    public List<Course> getCoursesList() {
        if(energySystem.canPractice(isPremium) && !availableCourses.isEmpty())
            return coursesList;
        return new ArrayList<>();
    }


    public EnergySystem getEnergySystem(){
        return energySystem;
    }

    public void setPremium(boolean premium) {
        energySystem.setPremium();
        this.isPremium=premium;
        String premiumMessage = premium ? "Premium mode text appears on upper corner left" : "Free mode text appears on upper corner left";
        logger.logChange(premiumMessage, username, this.toString());
    }

    public Streak getStreak() {
        return streak;
    }

    public Course getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(Course selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    public String getHintForExercise(Exercise exercise) {
        if (isPremium) {
            return exercise.getHint();
        } else {
            return "Pay us for hint";
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "currentEnergy=" + energySystem.getCurrentEnergy() +
                ", isPremium=" + isPremium +
                ", streak=" + streak +
                ", username='" + username + '\'' +
                ", coursesList=" + coursesList +
                '}';
    }
}
