package com.jalat.execution;

import com.jalat.description.*;
import com.jalat.error.MethodIsNotAllowedHereException;
import com.jalat.util.Stopwatch;

import javax.annotation.Nonnull;

/**
 * Use-case execution strategy
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
final class UseCaseExecution extends BaseExecutionStrategy<UseCaseDescription> {
    UseCaseExecution(@Nonnull UseCaseDescription root, @Nonnull RecorderStrategy parent) {
        super(root, parent);
    }

    @Override
    public void registerPreCondition(PreConditionDescription preConditionDescription) throws Exception {
        if (!(currentObject instanceof UseCaseDescription)) {
            throw new MethodIsNotAllowedHereException("preCondition", "scenario initialization", "useCase");
        }
        root.setPreCondition(preConditionDescription);
        final Stopwatch stepStopwatch = new Stopwatch(true);
        final BaseDescription previous = this.currentObject;
        try {
            preConditionDescription.getBody().evaluate();
        } finally {
            preConditionDescription.setDuration(stepStopwatch.stop());
            currentObject = previous;
        }
    }

    @Override
    public void registerPostCondition(PostConditionDescription postConditionDescription) throws Exception {
        if (!(currentObject instanceof UseCaseDescription)) {
            throw new MethodIsNotAllowedHereException("postCondition", "scenario initialization", "useCase");
        }
        root.setPostCondition(postConditionDescription);
        final Stopwatch stepStopwatch = new Stopwatch(true);
        final BaseDescription previous = this.currentObject;
        try {
            postConditionDescription.getBody().evaluate();
        } finally {
            postConditionDescription.setDuration(stepStopwatch.stop());
            currentObject = previous;
        }
    }

    @Override
    public void addCheck(@Nonnull CheckDescription checkDescription) throws Exception {
        if (!(currentObject instanceof CheckHolder)) {
            throw new MethodIsNotAllowedHereException("check", "useCase", "step");
        }
        final CheckHolder previous = (CheckHolder) this.currentObject;
        final Stopwatch stepStopwatch = new Stopwatch(true);
        previous.addCheck(checkDescription);
        this.currentObject = checkDescription;
        try {
            checkDescription.getBody().evaluate();
        } finally {
            checkDescription.setDuration(stepStopwatch.stop());
            currentObject = (BaseDescription) previous;
        }
    }
}
