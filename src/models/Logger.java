package models;

import java.util.List;
import java.util.Stack;

public class Logger { //SINGLETON

    private final Stack<String> history;
    private static Logger instance;

    private Logger() {
        history = new Stack<>();
    }


    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void logChange(String message) {
        history.push(message);
        System.out.println(message);
    }

    public List<String> getHistory() {
        return history;
    }
}
