package com.jalat.execution;

import com.jalat.description.PreConditionDescription;

import javax.annotation.Nonnull;

/**
 * Implementation of the {@link RecorderStrategy} to execute Pre-Condition of some {@link com.jalat.Scenario}
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
final class PreConditionExecution extends BaseExecutionStrategy<PreConditionDescription> {
    PreConditionExecution(@Nonnull PreConditionDescription preCondition, @Nonnull RecorderStrategy parent) {
        super(preCondition, parent);
    }
}
