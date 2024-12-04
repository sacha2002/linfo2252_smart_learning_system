package models.Energy;

import views.observers.Observer;

import javax.swing.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class EnergySystem{

    private static final int MAX_ENERGY = 30;
    private int currentEnergy = MAX_ENERGY;
    private LocalDateTime lastRechargeTime = LocalDateTime.now();
    private static final Duration RECHARGE_INTERVAL = Duration.ofMinutes(1);

    private Timer energyTimer;

    private final List<Observer> observers = new ArrayList<>();

    public EnergySystem() {
        startEnergyTimer();
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }


    public void useEnergy(boolean isPremium) {
        if( isPremium)
            return;
        currentEnergy--;
        notifyObservers("Free Mode: "+ currentEnergy +"/"+ MAX_ENERGY);
    }

    public int getMaxEnergy() {
        return MAX_ENERGY;
    }


    public boolean canPractice(boolean isPremium) {
        if(isPremium)
            return true;
        boolean canPractice = currentEnergy > 0;
        String message = canPractice? "" : "Wait for energy to recharge";
        notifyObservers("Free Mode: "+ message);
        return canPractice;
    }


    public void rechargeEnergy() {
        if(currentEnergy>=MAX_ENERGY)
            return;
        LocalDateTime now = LocalDateTime.now();
        long minutesElapsed = Duration.between(lastRechargeTime, now).toMinutes();

        int energyToRecharge = (int) (minutesElapsed / RECHARGE_INTERVAL.toMinutes());
        if(energyToRecharge>0){
            currentEnergy+=  Math.min(energyToRecharge, MAX_ENERGY);
            lastRechargeTime = now;
            notifyObservers("Free Mode: "+ currentEnergy +"/"+ MAX_ENERGY);
        }

    }

    private void startEnergyTimer() {
        energyTimer = new Timer(10000, e -> {
            rechargeEnergy();
        });
        energyTimer.start();
    }



    public void setPremium() {
        currentEnergy= MAX_ENERGY;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
        notifyObservers("Free Mode: " + currentEnergy +"/"+ MAX_ENERGY);
    }

    public void removeObserver(Observer observer) {
        notifyObservers("Premium Mode");
        observers.remove(observer);
    }

    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }


}
