package models;

import models.Exercises.Exercise;
import models.courses.Course;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
     private final List<Course> coursesList = new ArrayList<>();
     private final List<User> friendsList = new ArrayList<>();
     private String username;
     private int streak;


    private static final int MAX_ENERGY = 10;
    private int currentEnergy;
    private LocalDateTime lastRechargeTime;
    private static final Duration rechargeInterval = Duration.ofMinutes(5);


    public List<Course> getCoursesList() {
        return coursesList;
    }

    public void enrollFromCourse(Course course) {
        coursesList.add(course);
    }

    public void deEnrollFromCourse(Course course) {
        coursesList.remove(course);
    }

    public List<User> getFriendsList() {
        return friendsList;
    }

    public void addFriend(User user) {
        friendsList.add(user);
        user.addFriend(this); //mutually adding
    }

    public void deleteFriend(User user){
        friendsList.remove(user);
        user.deleteFriend(this);//mutually delete
    }

    //Recharge energy based on time passed
    private void rechargeEnergy() {
        LocalDateTime now = LocalDateTime.now();
        long minutesElapsed = Duration.between(lastRechargeTime, now).toMinutes();


        int energyToRecharge = (int) (minutesElapsed / rechargeInterval.toMinutes());

        if (energyToRecharge > 0) {
            currentEnergy = Math.min(MAX_ENERGY, currentEnergy + energyToRecharge);
            lastRechargeTime = now; // Update last recharge time to now
        }
    }

    //delete energy and check if can practice todo just check for energy
    public boolean practice() {
        rechargeEnergy();
        if (currentEnergy > 0) {
            currentEnergy--; // Consume energy
            System.out.println(username + " practiced! Current energy: " + currentEnergy);
            return true; // Practice successful
        } else {
            System.out.println(username + " does not have enough energy to practice.");
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
    }

    public void setEnergy(int energy) {
        this.currentEnergy = Math.min(MAX_ENERGY, Math.max(0, energy)); // just for testing in future
    }

}
