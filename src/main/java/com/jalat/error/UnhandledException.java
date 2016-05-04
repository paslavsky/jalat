package com.jalat.error;

/**
 * This class using to wrap checked and unhandled exceptions
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public class UnhandledException extends JaLaTException {
    public UnhandledException(Throwable cause) {
        super(cause);
    }
}
