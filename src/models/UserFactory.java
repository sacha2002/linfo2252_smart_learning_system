package models;

import models.courses.Course;

import java.util.List;

public class UserFactory { //just the patter to make the user creation easier as it has many parameters
//User user1 = UserFactory.createUser("messi");
    // Factory method to create a user with a username
    public static User createUser(String username) {
        User user = new User();
        user.setUsername(username);
        user.setStreak(0);
        return user;
    }

    // Factory method to create a user with a username and initial streak
    public static User createUser(String username, int streak) {
        User user = new User();
        user.setUsername(username);
        user.setStreak(streak);
        return user;
    }

    // Optional: Factory method to create a user with initial courses and friends
    public static User createUser(String username, int streak, List<Course> initialCourses, List<User> initialFriends) {
        User user = createUser(username, streak);

        for (Course course : initialCourses) {
            user.enrollFromCourse(course);
        }

        for (User friend : initialFriends) {
            user.addFriend(friend);
        }

        return user;
    }
}

