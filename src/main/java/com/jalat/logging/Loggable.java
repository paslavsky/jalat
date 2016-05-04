package com.jalat.logging;

import com.jalat.PublicAPI;

import javax.annotation.Nonnull;
import java.text.MessageFormat;

/**
 * This class allow to extend any class with logging functions
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
@PublicAPI
@SuppressWarnings("unused")
public interface Loggable {
    /**
     * Create new {@link Logger} by {@link #getClass() this class}
     * @return new logger
     */
    default @Nonnull Logger getLogger() {
        return LoggerFactory.getLogger(getClass());
    }

    default void logError(@Nonnull String message) {
        getLogger().error(() -> message, null);
    }

    default void logError(@Nonnull String message, @Nonnull Throwable e) {
        getLogger().error(() -> message, e);
    }

    default void logError(@Nonnull String message, @Nonnull Object... params) {
        getLogger().error(() -> MessageFormat.format(message, params), null);
    }

    default void logError(@Nonnull String message, @Nonnull Throwable e, @Nonnull Object... params) {
        getLogger().error(() -> MessageFormat.format(message, params), e);
    }

    default void logWarning(@Nonnull String message) {
        getLogger().warning(() -> message, null);
    }

    default void logWarning(@Nonnull String message, @Nonnull Throwable e) {
        getLogger().warning(() -> message, e);
    }

    default void logWarning(@Nonnull String message, @Nonnull Object... params) {
        getLogger().warning(() -> MessageFormat.format(message, params), null);
    }

    default void logWarning(@Nonnull String message, @Nonnull Throwable e, @Nonnull Object... params) {
        getLogger().warning(() -> MessageFormat.format(message, params), e);
    }

    default void logInfo(@Nonnull String message) {
        getLogger().info(() -> message, null);
    }

    default void logInfo(@Nonnull String message, @Nonnull Throwable e) {
        getLogger().info(() -> message, e);
    }

    default void logInfo(@Nonnull String message, @Nonnull Object... params) {
        getLogger().info(() -> MessageFormat.format(message, params), null);
    }

    default void logInfo(@Nonnull String message, @Nonnull Throwable e, @Nonnull Object... params) {
        getLogger().info(() -> MessageFormat.format(message, params), e);
    }

    default void logDebug(@Nonnull String message) {
        getLogger().debug(() -> message, null);
    }

    default void logDebug(@Nonnull String message, @Nonnull Throwable e) {
        getLogger().debug(() -> message, e);
    }

    default void logDebug(@Nonnull String message, @Nonnull Object... params) {
        getLogger().debug(() -> MessageFormat.format(message, params), null);
    }

    default void logDebug(@Nonnull String message, @Nonnull Throwable e, @Nonnull Object... params) {
        getLogger().debug(() -> MessageFormat.format(message, params), e);
    }

    default void logTrace(@Nonnull String message) {
        getLogger().trace(() -> message, null);
    }

    default void logTrace(@Nonnull String message, @Nonnull Throwable e) {
        getLogger().trace(() -> message, e);
    }

    default void logTrace(@Nonnull String message, @Nonnull Object... params) {
        getLogger().trace(() -> MessageFormat.format(message, params), null);
    }

    default void logTrace(@Nonnull String message, @Nonnull Throwable e, @Nonnull Object... params) {
        getLogger().trace(() -> MessageFormat.format(message, params), e);
    }
}
