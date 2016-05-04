package com.jalat.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Stopwatch to identify process duration
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public final class Stopwatch {
    private final AtomicLong startTime = new AtomicLong(-1);

    public Stopwatch(boolean start) {
        if (start) {
            start();
        }
    }

    @SuppressWarnings("WeakerAccess")
    public void start() {
        startTime.set(System.currentTimeMillis());
    }

    public long stop() {
        if (!isStarted()) {
            throw new IllegalStateException("Stopwatch is not run");
        }
        return System.currentTimeMillis() - startTime.getAndSet(-1);
    }

    @SuppressWarnings("WeakerAccess")
    public boolean isStarted() {
        return startTime.get() > 0;
    }
}
