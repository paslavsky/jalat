package com.jalat.execution;

import com.jalat.Scenario;
import com.jalat.description.PostConditionDescription;
import com.jalat.description.PreConditionDescription;
import com.jalat.description.ScenarioDescription;
import com.jalat.description.UseCaseDescription;
import com.jalat.logging.LoggerFactory;
import com.jalat.report.ReportGenerator;
import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.Statement;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Root point of execution of the pre-/post-conditions and use-cases
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
final class ScenarioInvocation extends Statement {
    private final Scenario scenario;
    private final ScenarioRecorder recorder;
    private final RunNotifier notifier;

    ScenarioInvocation(Scenario scenario, ScenarioRecorder recorder, RunNotifier notifier) {
        this.scenario = scenario;
        this.recorder = recorder;
        this.notifier = notifier;
    }

    @Override
    public void evaluate() throws Throwable {
        recorder.startRecordScenario(scenario);
        try {
            Queue<Statement> queue = createInvocationQueue();
            for (Statement statement : queue) {
                statement.evaluate();
            }
        } finally {
            executePostCondition();
            ScenarioDescription scenarioDescription = recorder.stopRecordScenario(scenario);
            ReportGenerator.generateReport(scenario.getClass(), scenarioDescription);
        }
    }

    private Queue<Statement> createInvocationQueue() {
        Deque<Statement> invocationQueue = new LinkedList<>();

        Collection<UseCaseDescription> useCases = recorder.getScenarioDescription().getUseCases();
        invocationQueue.addAll(useCases.stream().map(UseCaseInvocation::new).collect(Collectors.toList()));

        PreConditionDescription preCondition = recorder.getScenarioDescription().getPreCondition();
        if (preCondition != null) {
            invocationQueue.addFirst(new PreConditionInvocation(preCondition));
        }

        return invocationQueue;
    }

    private void executePostCondition() {
        Statement statement = getPostConditionStatement();
        if (statement != null) {
            try {
                statement.evaluate();
            } catch (Throwable e) {
                LoggerFactory.getLogger(getClass()).error(() -> "Post condition failed", e);
            }
        }
    }

    private @Nullable Statement getPostConditionStatement() {
        PostConditionDescription postCondition = recorder.getScenarioDescription().getPostCondition();
        if (postCondition != null) {
            return new PostConditionInvocation(postCondition);
        }
        return null;
    }

    private class PreConditionInvocation extends Statement {
        private final PreConditionDescription preCondition;

        PreConditionInvocation(PreConditionDescription preCondition) {
            this.preCondition = preCondition;
        }

        @Override
        public void evaluate() throws Throwable {
            recorder.startRecordPreCondition(preCondition);
            try {
                preCondition.getBody().evaluate();
            } catch (Throwable e) {
                recorder.addFailure(e);
                throw e;
            } finally {
                recorder.stopRecordPreCondition();
            }
        }
    }

    private class PostConditionInvocation extends Statement {
        private final PostConditionDescription postCondition;

        PostConditionInvocation(PostConditionDescription postCondition) {
            this.postCondition = postCondition;
        }

        @Override
        public void evaluate() throws Throwable {
            recorder.startRecordPostCondition(postCondition);
            try {
                postCondition.getBody().evaluate();
            } catch (Throwable e) {
                recorder.addFailure(e);
                throw e;
            } finally {
                recorder.stopRecordPostCondition();
            }
        }
    }

    private class UseCaseInvocation extends Statement {
        private final UseCaseDescription useCase;

        UseCaseInvocation(UseCaseDescription useCase) {
            this.useCase = useCase;
        }

        @Override
        public void evaluate() throws Throwable {
            recorder.startRecordUseCase(useCase);
            EachTestNotifier eachNotifier = new EachTestNotifier(notifier, useCase.getJUnitDescription());
            eachNotifier.fireTestStarted();
            try {
                useCase.getBody().evaluate();
            } catch (AssumptionViolatedException e) {
                eachNotifier.addFailedAssumption(e);
                recorder.addFailure(e);
            } catch (Throwable e) {
                eachNotifier.addFailure(e);
                recorder.addFailure(e);
            } finally {
                eachNotifier.fireTestFinished();
                recorder.stopRecordUseCase();
            }
        }
    }
}
