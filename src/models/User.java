package models;

import models.courses.Course;

import java.util.ArrayList;
import java.util.List;

public class User {
     private final List<Course> coursesList = new ArrayList<>();
     private final List<User> friendsList = new ArrayList<>();
     private String username;
     private int streak;

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
}
