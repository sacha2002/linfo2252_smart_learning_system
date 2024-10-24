package models;

import models.Energy.EnergyStrategy;
import models.Energy.NormalUserEnergy;
import models.Energy.PremiumUserEnergy;
import models.courses.Course;

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
        logger.logChange("user: " + username + " has been created", username, this.toString());
    }

    //if he cant practice, the courses arent available
    public List<Course> getCoursesList() {
        if(energyStrategy.canPractice(currentEnergy))
            return coursesList;
        return new ArrayList<>();
    }

    public void enrollToCourse(Course course) {
        coursesList.add(course);
        logger.logChange("user: " + username +" enrolled in " + course.getName(), username,this.toString());
    }

    public void deEnrollToCourse(Course course) {
        coursesList.remove(course);
        logger.logChange("user: " + username +" Unenrolled in " + course.getName(), username,this.toString());
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
