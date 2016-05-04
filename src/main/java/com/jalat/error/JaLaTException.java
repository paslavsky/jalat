package com.jalat.error;

/**
 * {@link JaLaTException} is superclass for all errors inside {@code JaLaT} library
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class JaLaTException extends RuntimeException {
    public JaLaTException() {
    }

    public JaLaTException(String message) {
        super(message);
    }

    public JaLaTException(String message, Throwable cause) {
        super(message, cause);
    }

    public JaLaTException(Throwable cause) {
        super(cause);
    }
}
