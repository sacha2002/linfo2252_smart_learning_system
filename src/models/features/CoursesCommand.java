package models.features;

import models.Model;

public class CoursesCommand implements FeatureCommand{
    private final Model target;
    private final String name;

    public CoursesCommand(Model target) {
        this.target = target;
        name="COURSES";
    }
    @Override
    public String getName() {
        return name;
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
