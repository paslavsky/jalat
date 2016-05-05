package com.jalat.execution;

import com.jalat.Scenario;
import com.jalat.VoidFunction;
import com.jalat.description.*;
import com.jalat.error.IncorectBehaviorDetectedException;
import com.jalat.error.UnhandledException;

import javax.annotation.Nonnull;
import java.util.function.Function;

/**
 * This class allow to record scenario
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public final class ScenarioRecorder {
    private RecorderStrategy strategy = new InitScenario();

    public void setName(String name) {
        strategy.setName(name);
    }

    public void addUseCase(String name, VoidFunction useCaseImpl) {
        strategy.addUseCase(new UseCaseDescription(name, useCaseImpl));
    }

    public void setPreCondition(String shortDescription, VoidFunction preConditionImpl) {
        try {
            strategy.registerPreCondition(new PreConditionDescription(shortDescription, preConditionImpl));
        } catch (Exception e) {
            throw new UnhandledException(e);
        }
    }

    public void setPostCondition(String shortDescription, VoidFunction postConditionImpl) {
        try {
            strategy.registerPostCondition(new PostConditionDescription(shortDescription, postConditionImpl));
        } catch (Exception e) {
            throw new UnhandledException(e);
        }
    }

    public void setDescription(CharSequence[] description) {
        strategy.setDescription(description);
    }

    public void addStep(String shortDescription, VoidFunction stepImpl) throws Exception {
        strategy.addStep(new StepDescription(shortDescription, stepImpl));
    }

    public void addCheck(String name, VoidFunction checkImpl) {
        try {
            strategy.addCheck(new CheckDescription(name, checkImpl));
        } catch (Exception e) {
            throw new UnhandledException(e);
        }
    }

    @Nonnull
    ScenarioDescription getScenarioDescription() {
        return strategy.getScenarioDescription();
    }

    void startRecordPreCondition(@Nonnull PreConditionDescription preCondition) {
        strategy = new PreConditionExecution(preCondition, strategy);
    }

    void stopRecordPreCondition() {
        completeCurrentStrategy();
    }

    void startRecordPostCondition(@Nonnull PostConditionDescription postCondition) {
        strategy = new PostConditionExecution(postCondition, strategy);
    }

    void stopRecordPostCondition() {
        completeCurrentStrategy();
    }

    void startRecordUseCase(@Nonnull UseCaseDescription useCase) {
        strategy = new UseCaseExecution(useCase, strategy);
    }

    void stopRecordUseCase() {
        completeCurrentStrategy();
    }

    private void completeCurrentStrategy() {
        strategy = check(strategy.complete(), recorderStrategy ->
                recorderStrategy instanceof InitScenario ? null :
                        new IncorectBehaviorDetectedException("The internal logic of execution violated")
        );
    }

    void addFailure(@Nonnull Throwable error) {
        strategy.addFailure(error);
    }

    void startRecordScenario(Scenario scenario) {
        ExecutionPropertiesLookup propertiesLookupFunction = (param) ->
        {
            switch (param) {
                case SHORT_DESCRIPTION:
                    return strategy.getShortDescription();
                default:
                    throw new IncorectBehaviorDetectedException("Undefined runtime context parameter");
            }
        };
        ExecutionContext.bind(scenario, propertiesLookupFunction);
    }

    ScenarioDescription stopRecordScenario(Scenario scenario) {
        ExecutionContext.drop(scenario);
        check(strategy, recorderStrategy ->
                recorderStrategy instanceof InitScenario ? null :
                        new IncorectBehaviorDetectedException("The internal logic of execution violated")
        ).complete();
        return strategy.getScenarioDescription();
    }

    private static <T> T check(T value, Function<T, RuntimeException> validation) {
        RuntimeException error = validation.apply(value);
        if (error != null) {
            throw error;
        }
        return value;
    }
}
