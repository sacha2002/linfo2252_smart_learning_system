package models;

import models.Energy.EnergyStrategy;
import models.Energy.NormalUserEnergy;
import models.Energy.PremiumUserEnergy;
import models.Exercises.Exercise;
import models.Exercises.ExerciseData;
import models.courses.Course;
import models.courses.English;
import models.courses.French;
import models.courses.Spanish;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private final Logger logger = Logger.getInstance();

     private final List<Course> coursesList = new ArrayList<>();
     private String username;

     private int streak;
     private LocalDate lastPracticeDate;

    private boolean isPremium;
    private int currentEnergy;
    private LocalDateTime lastRechargeTime;
    private static final Duration rechargeInterval = Duration.ofMinutes(5);
    private EnergyStrategy energyStrategy;

    public Model(String username, int streak, boolean isPremium, int currentEnergy) {
        this.username = username;
        this.streak = streak;
        this.isPremium = isPremium;
        this.currentEnergy = currentEnergy;
        this.energyStrategy = isPremium ? new PremiumUserEnergy() : new NormalUserEnergy();
        //all rank in silver just for demo
        coursesList.add(new Spanish(650, ExerciseData.getSpanishSilverExercises()));
        coursesList.add(new French(650, ExerciseData.getFrenchSilverExercises()));
        coursesList.add(new English(650, ExerciseData.getEnglishSilverExercises()));

        logger.logChange("user: " + username + " has been created", username, this.toString());
    }

    //if he cant practice, the courses arent available
    public List<Course> getCoursesList() {
        if(energyStrategy.canPractice(currentEnergy))
            return coursesList;
        return new ArrayList<>();
    }


    //Recharge energy based on time passed
    private void rechargeEnergy() {
        currentEnergy = energyStrategy.rechargeEnergy(currentEnergy, lastRechargeTime, rechargeInterval);
        lastRechargeTime = LocalDateTime.now();
        logger.logChange("user: " + username + " has recharged their energy", username, this.toString());
    }

    public boolean practice() {
        rechargeEnergy();
        if (energyStrategy.canPractice(currentEnergy)) {
            currentEnergy--;
            logger.logChange(username + " practiced! Current energy: " + currentEnergy, username, this.toString());
            return true;
        } else {
            logger.logChange(username + " does not have enough energy to practice.", username, this.toString());
            return false;
        }
    }

    public String getUsername() {
        return username;
    }

    public int getStreak() {
        return streak;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Course getCourse(String courseName){
        for (Course c : coursesList) {
            if(courseName.equals(c.getName())){
                return c;
            }
        }
        return null;
    }



    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
        energyStrategy = isPremium ? new PremiumUserEnergy() : new NormalUserEnergy();
        String premiumMessage = isPremium ? "is now premium" : "is no longer premium";
        logger.logChange(username + " " + premiumMessage, username, this.toString());
    }

    @Override
    public String toString() {
        return "User{" +
                "currentEnergy=" + currentEnergy +
                ", isPremium=" + isPremium +
                ", streak=" + streak +
                ", username='" + username + '\'' +
                ", coursesList=" + coursesList +
                '}';
    }
}
