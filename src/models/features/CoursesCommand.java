package models.features;

import models.Model;

public class CoursesCommand implements FeatureCommand{
    private final Model target;

    public CoursesCommand(Model target) {
        this.target = target;
    }
    @Override
    public String getName() {
        return "COURSES";
    }

    @Override
    public void execute() {
        target.updateAvailableCourses(true);
    }

    @Override
    public void undo() {
        target.updateAvailableCourses(false);
    }
}
