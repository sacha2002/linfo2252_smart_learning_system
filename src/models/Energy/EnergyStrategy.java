package models.Energy;

import java.time.Duration;
import java.time.LocalDateTime;

public interface EnergyStrategy {
    int getMaxEnergy();
    boolean canPractice(int currentEnergy);
    int rechargeEnergy(int currentEnergy, LocalDateTime lastRechargeTime, Duration rechargeInterval);
}

//strategy pattern to adapt to adding more energy systems in the future
