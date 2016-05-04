package com.jalat.logging;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

/**
 * Logger over {@code SLF4J}
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
final class Slf4jLogger implements Logger {
    private final org.slf4j.Logger logger;

    Slf4jLogger(Class<?> aCLass) {
        logger = org.slf4j.LoggerFactory.getLogger(aCLass);
    }

    @Override
    public void error(@Nonnull Supplier<String> message, @Nullable Throwable error) {
        if (logger.isErrorEnabled()) {
            logger.error(message.get(), error);
        }
    }

    @Override
    public void warning(@Nonnull Supplier<String> message, @Nullable Throwable error) {
        if (logger.isWarnEnabled()) {
            logger.warn(message.get(), error);
        }
    }

    @Override
    public void info(@Nonnull Supplier<String> message, @Nullable Throwable error) {
        if (logger.isInfoEnabled()) {
            logger.info(message.get(), error);
        }
    }

    @Override
    public void debug(@Nonnull Supplier<String> message, @Nullable Throwable error) {
        if (logger.isDebugEnabled()) {
            logger.debug(message.get(), error);
        }
    }

    @Override
    public void trace(@Nonnull Supplier<String> message, @Nullable Throwable error) {
        if (logger.isTraceEnabled()) {
            logger.trace(message.get(), error);
        }
    }
}
