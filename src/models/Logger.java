package models;

import java.util.*;

public class Logger { //SINGLETON

    private final Stack<String> history;
    private static Logger instance;
    private Map<String, String> currentStates;

    private Logger() {
        history = new Stack<>();
        currentStates = new HashMap<>();
    }


    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void logChange(String message, String modelName, String stateDescription) {
        history.push(message);
        updateCurrentState(modelName, stateDescription);
        System.out.println(message);
    }

    public List<String> getHistory() {
        return history;
    }

    private void updateCurrentState(String modelName, String stateDescription) {
        currentStates.put(modelName, stateDescription); // Update the state
    }

    public String getCurrentState(String modelName) {
        return currentStates.getOrDefault(modelName, "No current state available for " + modelName);
    }

    public List<String> getAllCurrentStates() {
        List<String> states = new ArrayList<>();
        for (Map.Entry<String, String> entry : currentStates.entrySet()) {
            states.add(entry.getKey() + ": " + entry.getValue());
        }
        return states;
    }

    public void clearLogs() {
        history.clear();
    }
}
