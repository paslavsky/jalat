package com.jalat.description;

import com.jalat.VoidFunction;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * One step description
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public final class StepDescription extends BaseDescription implements CheckHolder {
    private final List<CheckDescription> checks = new ArrayList<>();

    public StepDescription(String shortDescription, VoidFunction body) {
        super(shortDescription, body);
    }

    @Override
    public void addCheck(@Nonnull CheckDescription check) {
        checks.add(check);
    }

    @Nonnull
    @Override
    public Collection<CheckDescription> getChecks() {
        return Collections.unmodifiableCollection(checks);
    }
}
