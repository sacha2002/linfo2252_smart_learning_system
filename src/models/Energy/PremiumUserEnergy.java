package models.Energy;

import java.time.Duration;
import java.time.LocalDateTime;

public class PremiumUserEnergy implements EnergyStrategy {

    @Override
    public int getMaxEnergy() {
        return Integer.MAX_VALUE; // unlimited energy
    }

    @Override
    public boolean canPractice(int currentEnergy) {
        return true; // Premium users can always practice
    }

    @Override
    public int rechargeEnergy(int currentEnergy, LocalDateTime lastRechargeTime, Duration rechargeInterval) {
        return Integer.MAX_VALUE; // Always full
    }
}
