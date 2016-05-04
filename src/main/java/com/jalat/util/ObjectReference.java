package com.jalat.util;

import javax.annotation.Nonnull;

/**
 * Wrap any object and provides {@link #equals(Object)} and {@link #hashCode()} methods that based on object reference.
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public final class ObjectReference {
    @Nonnull
    private final Object obj;

    public ObjectReference(final @Nonnull Object obj) {
        this.obj = obj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectReference that = (ObjectReference) o;
        return obj == that.obj;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(obj);
    }
}
