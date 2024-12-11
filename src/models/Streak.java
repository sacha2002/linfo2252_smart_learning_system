package models;

import views.observers.Observer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Streak {
    private int streak;
    private LocalDate lastPracticeDate = LocalDate.now().minusDays(1);


    private final Logger logger = Logger.getInstance();


    private final List<Observer> observers = new ArrayList<>();

    public Streak(int streak) {
        this.streak = streak;
    }


    public void updateStreak() {
        LocalDate today = LocalDate.now();

        if (lastPracticeDate.equals(today.minusDays(1))) {
            streak++; // Extend the streak
        } else if (!lastPracticeDate.equals(today)) {
            streak=0; //reset
        }
        notifyObservers(Integer.toString(streak));
        lastPracticeDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Streak{" +
                "streak=" + streak +
                ", lastPracticeDate=" + lastPracticeDate +
                '}';
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
        notifyObservers(Integer.toString(streak));
    }


    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}