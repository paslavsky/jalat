package com.jalat.logging;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;
import java.util.logging.Level;

/**
 * ogger over standard Java logging API
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
final class JdkLogger implements Logger {
    private final java.util.logging.Logger logger;

    JdkLogger(Class<?> aClass) {
        logger = java.util.logging.Logger.getLogger(aClass.getName());
    }

    @Override
    public void error(@Nonnull Supplier<String> message, @Nullable Throwable error) {
        log(Level.SEVERE, message, error);
    }

    @Override
    public void warning(@Nonnull Supplier<String> message, @Nullable Throwable error) {
        log(Level.WARNING, message, error);
    }

    @Override
    public void info(@Nonnull Supplier<String> message, @Nullable Throwable error) {
        log(Level.INFO, message, error);
    }

    @Override
    public void debug(@Nonnull Supplier<String> message, @Nullable Throwable error) {
        log(Level.CONFIG, message, error);
    }

    @Override
    public void trace(@Nonnull Supplier<String> message, @Nullable Throwable error) {
        log(Level.FINE, message, error);
    }

    private void log(@Nonnull Level level, @Nonnull Supplier<String> message, @Nullable Throwable error) {
        if (logger.isLoggable(level)) {
            logger.log(Level.SEVERE, error, message);
        }
    }
}
