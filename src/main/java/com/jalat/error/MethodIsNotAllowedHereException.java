package com.jalat.error;

import java.util.Arrays;

/**
 * TODO Describe class
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public class MethodIsNotAllowedHereException extends IncorectBehaviorDetectedException {
    public MethodIsNotAllowedHereException(String method, String... availableInside) {
        super("Method #" + method + " is not allowed here!" +
                (availableInside.length > 0 ? "It is available only inside " + Arrays.toString(availableInside) : ""));
    }
}
