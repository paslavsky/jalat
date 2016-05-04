package com.jalat.description;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Interface for objects that can contain {@link CheckDescription checks}
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public interface CheckHolder {
    void addCheck(@Nonnull CheckDescription check);

    @Nonnull Collection<CheckDescription> getChecks();
}
