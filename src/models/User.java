package models;

import models.courses.Course;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {

    private final Logger logger = Logger.getInstance();

     private final List<Course> coursesList = new ArrayList<>();
     private final List<User> friendsList = new ArrayList<>();
     private String username;
     private int streak;
     private boolean isPremium;


    private static final int MAX_ENERGY = 10;
    private int currentEnergy;
    private LocalDateTime lastRechargeTime;
    private static final Duration rechargeInterval = Duration.ofMinutes(5);

    public User(String username, int streak, boolean isPremium, int currentEnergy) {
        this.username = username;
        this.streak = streak;
        this.isPremium = isPremium;
        this.currentEnergy = currentEnergy;
        logger.logChange("user: " + username +" has been created");
    }

    public List<Course> getCoursesList() {
        return coursesList;
    }

    public void enrollFromCourse(Course course) {
        coursesList.add(course);
        logger.logChange("user: " + username +" enrolled in " + course.getName());
    }

    public void deEnrollFromCourse(Course course) {
        coursesList.remove(course);
        logger.logChange("user: " + username +" Unenrolled in " + course.getName());
    }

    public List<User> getFriendsList() {
        return friendsList;
    }

    public void addFriend(User user) {
        friendsList.add(user);
        user.addFriend(this); //mutually adding
        logger.logChange(username +" and  " + user.getUsername() + " are now friends ");
    }

    public void deleteFriend(User user){
        friendsList.remove(user);
        user.deleteFriend(this);//mutually delete
        logger.logChange(username +" and  " + user.getUsername() + " are no longer friends ");
    }

    //Recharge energy based on time passed
    private void rechargeEnergy() {
        LocalDateTime now = LocalDateTime.now();
        long minutesElapsed = Duration.between(lastRechargeTime, now).toMinutes();


        int energyToRecharge = (int) (minutesElapsed / rechargeInterval.toMinutes());

        if (energyToRecharge > 0) {
            currentEnergy = Math.min(MAX_ENERGY, currentEnergy + energyToRecharge);
            lastRechargeTime = now;
            logger.logChange("user: " + username +" has recharged his energy");
        }
    }

    //delete energy and check if can practice todo just check for energy
    public boolean practice() {
        rechargeEnergy();
        if (currentEnergy > 0) {
            currentEnergy--;
            logger.logChange(username + " practiced! Current energy: " + currentEnergy);
            return true;
        } else {
            logger.logChange(username + " does not have enough energy to practice.");
            return false; // Not enough energy
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

    public void setStreak(int streak) {
        this.streak = streak;
        logger.logChange(username + "has now a streak of " + streak);
    }

    public void setEnergy(int energy) {
        this.currentEnergy = Math.min(MAX_ENERGY, Math.max(0, energy)); // just for testing in future
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;

        String premiumMessage = isPremium? "is now premium" : "is no longer premium";
        logger.logChange(username + premiumMessage);
    }
}
