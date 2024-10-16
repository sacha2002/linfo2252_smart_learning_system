package models;

import java.util.List;
import java.util.Stack;

public class Logger {

    private final Stack<String> history = new Stack<>();

    public void logChange(String message) {
        history.push(message);
        System.out.println(message);
    }

    public List<String> getHistory() {
        return history;
    }
}
