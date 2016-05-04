package com.jalat.execution;

import com.jalat.description.PostConditionDescription;
import com.jalat.description.PreConditionDescription;
import com.jalat.description.ScenarioDescription;
import com.jalat.description.UseCaseDescription;
import com.jalat.error.IncorectBehaviorDetectedException;
import com.jalat.error.UnhandledException;
import com.jalat.util.InitializeOnlyOnce;
import com.jalat.util.Stopwatch;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * This strategy implements base logic to initialize {@link com.jalat.Scenario} before run
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
class InitScenario implements RecorderStrategy, InitializeOnlyOnce {
    private final ScenarioDescription scenarioDescription = new ScenarioDescription();
    private final Stopwatch stopwatch = new Stopwatch(true);

    @Nonnull
    @Override
    public ScenarioDescription getScenarioDescription() {
        return scenarioDescription;
    }

    @Nullable
    @Override
    public RecorderStrategy getParentStrategy() {
        return null;
    }

    @Override
    public void setName(String name) {
        scenarioDescription.setName(name);
    }

    @Override
    public void addUseCase(UseCaseDescription useCaseDescription) {
        scenarioDescription.addUseCase(useCaseDescription);
    }

    @Override
    public void registerPreCondition(PreConditionDescription preConditionDescription) {
        scenarioDescription.setPreCondition(preConditionDescription);
    }

    @Override
    public void registerPostCondition(PostConditionDescription postConditionDescription) {
        scenarioDescription.setPostCondition(postConditionDescription);
    }

    @Override
    public void setDescription(@Nonnull CharSequence[] description) {
        scenarioDescription.setDescription(initOnce(description, scenarioDescription.getDescription(), () -> {
            throw new IncorectBehaviorDetectedException("Please use method #description only once inside one statement");
        }));
    }

    @Nonnull
    @Override
    public String getShortDescription() {
        return scenarioDescription.getName();
    }

    @Nonnull
    @Override
    public RecorderStrategy complete() {
        long duration = stopwatch.stop();
        scenarioDescription.setDuration(duration);
        return this;
    }

    @Override
    public void addFailure(@Nonnull Throwable error) {
        throw new UnhandledException(error);
    }
}
