package models.features;

import models.Model;

public class PremiumCommand implements FeatureCommand{
    private final Model target;

    public PremiumCommand(Model target) {
        this.target = target;
    }
    @Override
    public String getName() {
        return "PREMIUM";
    }

    @Override
    public void execute() {
        target.setPremium(true);
    }

    @Override
    public void undo() {
        target.setPremium(false);
    }
}
