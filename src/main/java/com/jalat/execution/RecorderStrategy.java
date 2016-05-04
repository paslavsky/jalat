package com.jalat.execution;

import com.jalat.description.*;
import com.jalat.error.MethodIsNotAllowedHereException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * This interface define strategy of the {@link ScenarioRecorder}
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
interface RecorderStrategy {
    default @Nonnull ScenarioDescription getScenarioDescription() {
        if (getParentStrategy() == null) {
            throw new UnsupportedOperationException("Method getScenarioDescription() not implemented!");
        }
        return getParentStrategy().getScenarioDescription();
    }

    @Nullable RecorderStrategy getParentStrategy();

    default void setName(String name) {
        throw new MethodIsNotAllowedHereException("setName", "scenario initialization");
    }

    default void addUseCase(UseCaseDescription useCaseDescription) {
        throw new MethodIsNotAllowedHereException("useCase", "scenario initialization");
    }

    default void registerPreCondition(PreConditionDescription preConditionDescription) throws Exception {
        throw new MethodIsNotAllowedHereException("preCondition", "scenario initialization", "useCase");
    }

    default void registerPostCondition(PostConditionDescription postConditionDescription) throws Exception {
        throw new MethodIsNotAllowedHereException("postCondition", "scenario initialization", "useCase");
    }

    void setDescription(@Nonnull CharSequence[] description);

    @Nonnull
    String getShortDescription();

    default void addStep(@Nonnull StepDescription stepDescription) throws Exception {
        throw new MethodIsNotAllowedHereException("step", "preProcessor", "useCase", "step", "postProcessor");
    }

    default void addCheck(@Nonnull CheckDescription checkDescription) throws Exception {
        throw new MethodIsNotAllowedHereException("check", "useCase", "step");
    }

    @Nonnull RecorderStrategy complete();

    void addFailure(@Nonnull Throwable error);
}
