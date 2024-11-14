package models.Energy;

import models.Observer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EnergySystem{
    private static final int MAX_ENERGY = 30;
    private int currentEnergy = 30;
    private LocalDateTime lastRechargeTime = LocalDateTime.now();
    private static final Duration RECHARGE_INTERVAL = Duration.ofMinutes(1);
    private boolean isPremium;

    private final List<Observer> observers = new ArrayList<>();

    public EnergySystem(boolean isPremium) {
        this.isPremium = isPremium;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }


    public void useEnergy() {
        currentEnergy--;
        notifyObservers( currentEnergy +"/"+ MAX_ENERGY);
    }



    public int getMaxEnergy() {
        return MAX_ENERGY;
    }


    public boolean canPractice() {
        boolean canPractice = currentEnergy > 0;
        String message = canPractice? "" : "Wait for energy to recharge";
        notifyObservers(message);
        return canPractice;
    }


    public void rechargeEnergy() {
        LocalDateTime now = LocalDateTime.now();
        long minutesElapsed = Duration.between(lastRechargeTime, now).toMinutes();
        lastRechargeTime=now;
        int energyToRecharge = (int) (minutesElapsed / RECHARGE_INTERVAL.toMinutes());
        currentEnergy+=energyToRecharge;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
