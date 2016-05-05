package com.jalat.execution;

import javax.annotation.Nonnull;

/**
 * Interface to lookup
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
@FunctionalInterface
interface ExecutionPropertiesLookup {
    @Nonnull Object lookup(ExecutionProperty property);
}
