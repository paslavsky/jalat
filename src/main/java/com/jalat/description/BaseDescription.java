package com.jalat.description;

import com.jalat.VoidFunction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Base description object
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public abstract class BaseDescription {
    private final String shortDescription;
    private final VoidFunction body;
    private final List<StepDescription> steps = new ArrayList<>();
    private CharSequence[] description;
    private long duration;
    private final List<Throwable> errors = new ArrayList<>();

    BaseDescription(String shortDescription, VoidFunction body) {
        this.shortDescription = shortDescription;
        this.body = body;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public VoidFunction getBody() {
        return body;
    }

    public void setDescription(CharSequence[] description) {
        this.description = description;
    }

    public CharSequence[] getDescription() {
        return description;
    }

    public Collection<StepDescription> getSteps() {
        return Collections.unmodifiableCollection(steps);
    }

    public void addStep(StepDescription step) {
        steps.add(step);
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getDuration() {
        return duration;
    }

    public void addFailure(Throwable error) {
        errors.add(error);
    }

    public Collection<Throwable> getErrors() {
        return Collections.unmodifiableCollection(errors);
    }

    public boolean isFailed() {
        return !errors.isEmpty();
    }
}
