package com.jalat.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

/**
 * This interface has default function that allow to restrict from second initialization
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public interface InitializeOnlyOnce {
    default <T> T initOnce(@Nonnull T newValue, @Nullable T oldValue, Supplier<RuntimeException> error) {
        if (oldValue != null) {
            throw error.get();
        }
        return newValue;
    }
}
