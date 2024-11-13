package models.features;

public interface FeatureCommand {
    void execute();
    void undo();
    String getName();
}

