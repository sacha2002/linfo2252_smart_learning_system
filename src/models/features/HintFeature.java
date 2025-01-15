package models.features;

import models.Model;
import views.MainView;

public class HintFeature implements FeatureCommand {

    private final Model target;
    private final String name = "HINT";

    public HintFeature(Model target) {
        this.target = target;
    }

    @Override
    public void execute() {
        target.setHint(true);
    }

    @Override
    public void undo() {
        target.setHint(false);
    }

    @Override
    public String getName() {
        return name;
    }
}
