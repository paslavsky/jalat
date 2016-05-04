package com.jalat.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Utilities methods that makes some string checks
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public interface StringChecks {
    /**
     * Check that string {@code s} has text. If not then throws {@link IllegalArgumentException}
     * @param s Input string that need to check
     * @param argumentName Argument name for user friendly message in case of error
     * @return the same string if it is not empty
     * @throws IllegalArgumentException throws when string is empty
     */
    default String hasText(@Nullable String s, @Nonnull String argumentName) throws IllegalArgumentException {
        if (s == null) throw new IllegalArgumentException(argumentName + " can't be null!");
        if (s.trim().length() == 0) throw new IllegalArgumentException(argumentName + " can't be empty!");
        return s;
    }
}
