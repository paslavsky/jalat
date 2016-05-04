package com.jalat.execution;

import com.jalat.description.BaseDescription;
import com.jalat.description.StepDescription;
import com.jalat.error.IncorectBehaviorDetectedException;
import com.jalat.util.InitializeOnlyOnce;
import com.jalat.util.Stopwatch;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Super class for execution strategy
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
abstract class BaseExecutionStrategy<T extends BaseDescription> implements RecorderStrategy, InitializeOnlyOnce {
    @Nonnull
    final T root;
    @Nonnull
    private final RecorderStrategy parent;
    private final Stopwatch stopwatch = new Stopwatch(true);
    @Nonnull
    BaseDescription currentObject;

    BaseExecutionStrategy(@Nonnull T root, @Nonnull RecorderStrategy parent) {
        this.parent = parent;
        this.root = root;
        currentObject = root;
    }

    @Nullable
    @Override
    public RecorderStrategy getParentStrategy() {
        return parent;
    }

    @Override
    public void setDescription(@Nonnull CharSequence[] description) {
        root.setDescription(initOnce(description, root.getDescription(), () -> {
            throw new IncorectBehaviorDetectedException("Please use method #description only once inside one statement");
        }));
    }

    @Nonnull
    @Override
    public String getShortDescription() {
        return currentObject.getShortDescription();
    }

    @Override
    public void addStep(@Nonnull StepDescription stepDescription) throws Exception {
        final BaseDescription previous = this.currentObject;
        final Stopwatch stepStopwatch = new Stopwatch(true);
        previous.addStep(stepDescription);
        this.currentObject = stepDescription;
        try {
            stepDescription.getBody().evaluate();
        } finally {
            stepDescription.setDuration(stepStopwatch.stop());
            currentObject = previous;
        }
    }

    @Nonnull
    @Override
    public RecorderStrategy complete() {
        root.setDuration(stopwatch.stop());
        return parent;
    }

    @Override
    public void addFailure(@Nonnull Throwable error) {
        currentObject.addFailure(error);
    }
}
