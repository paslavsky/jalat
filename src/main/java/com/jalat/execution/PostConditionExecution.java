package com.jalat.execution;

import com.jalat.description.PostConditionDescription;

import javax.annotation.Nonnull;

/**
 * Implementation of the {@link RecorderStrategy} to execute Post-Condition of some {@link com.jalat.Scenario}
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
final class PostConditionExecution extends BaseExecutionStrategy<PostConditionDescription> {
    PostConditionExecution(@Nonnull PostConditionDescription postCondition, @Nonnull RecorderStrategy parent) {
        super(postCondition, parent);
    }
}
