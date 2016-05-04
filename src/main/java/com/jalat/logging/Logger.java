package com.jalat.logging;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

/**
 * Logger interface
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
@SuppressWarnings("WeakerAccess")
public interface Logger {
    void error(@Nonnull Supplier<String> message, @Nullable Throwable error);
    void warning(@Nonnull Supplier<String> message, @Nullable Throwable error);
    void info(@Nonnull Supplier<String> message, @Nullable Throwable error);
    void debug(@Nonnull Supplier<String> message, @Nullable Throwable error);
    void trace(@Nonnull Supplier<String> message, @Nullable Throwable error);
}
