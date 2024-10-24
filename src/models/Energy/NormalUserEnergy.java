package models.Energy;

import java.time.Duration;
import java.time.LocalDateTime;

public class NormalUserEnergy implements EnergyStrategy {
    private static final int MAX_ENERGY = 30;

    @Override
    public int getMaxEnergy() {
        return MAX_ENERGY;
    }

    @Override
    public boolean canPractice(int currentEnergy) {
        return currentEnergy > 0;
    }

    @Override
    public int rechargeEnergy(int currentEnergy, LocalDateTime lastRechargeTime, Duration rechargeInterval) {
        LocalDateTime now = LocalDateTime.now();
        long minutesElapsed = Duration.between(lastRechargeTime, now).toMinutes();
        int energyToRecharge = (int) (minutesElapsed / rechargeInterval.toMinutes());

        return Math.min(MAX_ENERGY, currentEnergy + energyToRecharge);
    }
}
