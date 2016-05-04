package com.jalat.execution;

import com.jalat.description.PostConditionDescription;

import javax.annotation.Nonnull;

/**
 * TODO
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
final class PostConditionExecution extends BaseExecutionStrategy<PostConditionDescription> {
    PostConditionExecution(@Nonnull PostConditionDescription postCondition, @Nonnull RecorderStrategy parent) {
        super(postCondition, parent);
    }
}
