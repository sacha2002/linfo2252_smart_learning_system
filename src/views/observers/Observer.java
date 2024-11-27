package views.observers;

import models.Exercises.Exercise;

public interface Observer {
    void update(String message);
    void update(Exercise message, int index); //just for rank-UP might have to change it later
}
