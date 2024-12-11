package models.features;

import models.Model;

public class EnglishCourseFeature implements FeatureCommand {

    private final Model target;
    private final String name = "ENGLISH";

    public EnglishCourseFeature(Model target) {
        this.target = target;
    }

    @Override
    public void execute() {
        target.updateAvailableCourses(true,name);
    }

    @Override
    public void undo() {
        target.updateAvailableCourses(false,name);
    }

    @Override
    public String getName() {
        return name;
    }
}
