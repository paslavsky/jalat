package com.jalat.error;

/**
 * This exception is thrown when detected incorrect behavior
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public class IncorectBehaviorDetectedException extends JaLaTException {
    public IncorectBehaviorDetectedException(String message) {
        super(message);
    }
}
