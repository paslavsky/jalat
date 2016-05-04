package com.jalat.error;

import com.jalat.Scenario;

/**
 * TODO Describe class
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public class ClassIsNotScenarioException extends JaLaTException {
    private final Class<?> invalidClass;

    public ClassIsNotScenarioException(Class<?> invalidClass) {
        super("Can't create scenario because class " + invalidClass.getName() + " is not assignable to " + Scenario.class.getName());
        this.invalidClass = invalidClass;
    }

    public Class<?> getInvalidClass() {
        return invalidClass;
    }
}
