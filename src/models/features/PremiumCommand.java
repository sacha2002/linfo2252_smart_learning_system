package models.features;

import models.Model;

public class PremiumCommand implements FeatureCommand{
    private final Model target;
    private final String name;

    public PremiumCommand(Model target) {
        this.target = target;
        name="PREMIUM";
    }
    @Override
    public String getName() {
        return name;
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
