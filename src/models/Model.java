package models;

import models.Energy.EnergySystem;
import models.Exercises.ExerciseData;
import models.courses.Course;
import models.courses.English;
import models.courses.French;
import models.courses.Spanish;
import models.features.CoursesCommand;
import models.features.FeatureCommand;
import models.features.PremiumCommand;


import java.time.LocalDate;
import java.util.*;

public class Model {

    private final Logger logger = Logger.getInstance();

     private final List<Course> coursesList = new ArrayList<>();
     private final List<String> availableCourses =  new ArrayList<>();
     private String username;

     private int streak;
     private LocalDate lastPracticeDate;


    private EnergySystem energySystem;

    private Set<FeatureCommand> activeFeatures = new HashSet<>();

    public Model(String username, int streak) {
        this.username = username;
        this.streak = streak;
        this.energySystem = new EnergySystem(false);
        //all rank in silver just for demo
        coursesList.add(new Spanish(650, ExerciseData.getSpanishSilverExercises()));
        coursesList.add(new French(650, ExerciseData.getFrenchSilverExercises()));
        coursesList.add(new English(998, ExerciseData.getEnglishSilverExercises()));
        //coursesList.add(new Spanish(650, ExerciseData.getAllSpanishExercices()));
        //coursesList.add(new English(650, ExerciseData.getAllEnglishExercices()));
        //coursesList.add(new French(650, ExerciseData.getAllFrenchExercices()));
        //init features
        activeFeatures.add(new CoursesCommand(this));
        activeFeatures.add(new PremiumCommand(this));

        logger.logChange("user: " + username + " has been created", username, this.toString());
    }


    public boolean practice() {
       // energySystem.rechargeEnergy();
        if (energySystem.canPractice()) {
            energySystem.useEnergy();
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

    public void updateAvailableCourses( boolean activate) {
            if (activate) {
                availableCourses.add("english");
                availableCourses.add("spanish");
                availableCourses.add("french");
            } else {
                availableCourses.clear();
            }
    }

    public List<String> getAvailableCourses(){
        return availableCourses;
    }


    public int getStreak() {
        return streak;
    }


    private void updateStreak() {
        LocalDate today = LocalDate.now();

        if (lastPracticeDate.equals(today.minusDays(1))) {
            streak++; // Extend the streak
        } else if (!lastPracticeDate.equals(today)) {
            streak = 0;
        }

        lastPracticeDate = today; // Update last practice date
        logger.logChange(username + " streak updated to: " + streak, username, this.toString());
    }


    //if it cant practice, no course is given( as second measure)
    public Course getCourse(String courseName){
        if(!energySystem.canPractice() && availableCourses.contains(courseName))
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
        if(energySystem.canPractice() && !availableCourses.isEmpty())
            return coursesList;
        return new ArrayList<>();
    }



    public EnergySystem getEnergySystem(){
        return energySystem;
    }

    public void setPremium(boolean premium) {
        energySystem.setPremium(premium);
        String premiumMessage = premium ? "is now premium" : "is no longer premium";
        logger.logChange(username + " " + premiumMessage, username, this.toString());
    }

    public boolean isPremium(){
        return energySystem.isPremium();
    }

    @Override
    public String toString() {
        return "User{" +
                "currentEnergy=" + energySystem.getCurrentEnergy() +
                ", isPremium=" + energySystem.isPremium() +
                ", streak=" + streak +
                ", username='" + username + '\'' +
                ", coursesList=" + coursesList +
                '}';
    }
}
