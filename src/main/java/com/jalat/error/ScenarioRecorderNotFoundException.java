package com.jalat.error;

/**
 * TODO Describe class
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public class ScenarioRecorderNotFoundException extends JaLaTException {
    public ScenarioRecorderNotFoundException(String message) {
        super(message);
    }

    public ScenarioRecorderNotFoundException(Throwable cause) {
        super(cause);
    }
}
