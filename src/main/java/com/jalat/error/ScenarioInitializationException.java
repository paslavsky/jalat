package com.jalat.error;

import com.jalat.Scenario;

/**
 * TODO Describe class
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public class ScenarioInitializationException extends JaLaTException {
    private final Class<? extends Scenario> scenarioClass;

    public ScenarioInitializationException(Class<? extends Scenario> scenarioClass, Throwable cause) {
        super("An unexpected error occurred during the creation of the " + scenarioClass.getName(), cause);
        this.scenarioClass = scenarioClass;
    }

    public Class<? extends Scenario> getScenarioClass() {
        return scenarioClass;
    }
}
