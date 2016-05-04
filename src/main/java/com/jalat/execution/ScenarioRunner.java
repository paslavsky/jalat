package com.jalat.execution;

import com.jalat.Scenario;
import com.jalat.description.UseCaseDescription;
import com.jalat.error.*;
import com.jalat.logging.LoggerFactory;
import com.jalat.util.ReflectionUtils;
import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.StoppedByUserException;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * Scenario runner.
 * This class allow to run one scenario and save its result as a text file
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public final class ScenarioRunner extends Runner {
    private static final String METHOD_GET_RECORDER = "getRecorder";
    private final Scenario scenario;
    private final ScenarioRecorder recorder;
    private JaLaTException exception = null;
    private Description jUnitTestDescriptions;

    public ScenarioRunner(@Nonnull Class<?> testClass) {
        scenario = getScenario(testClass);
        recorder = getScenarioRecorder(testClass);
        this.jUnitTestDescriptions = getJUnitTestDescription(testClass);
    }

    private Scenario getScenario(Class<?> testClass) {
        try {
            return createScenarioInstance(testClass);
        } catch (JaLaTException e) {
            this.exception = e;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static Scenario createScenarioInstance(Class<?> testClass) throws JaLaTException {
        if (!Scenario.class.isAssignableFrom(testClass)) {
            throw new ClassIsNotScenarioException(testClass);
        }
        Class<? extends Scenario> scenarioClass = (Class<? extends Scenario>) testClass;

        try {
            return scenarioClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ScenarioInitializationException(scenarioClass, e);
        }
    }

    private ScenarioRecorder getScenarioRecorder(@Nonnull Class<?> testClass) {
        if (exception == null) {
            try {
                return getScenarioRecorder0(scenario, testClass);
            } catch (JaLaTException e) {
                exception = e;
            }
        }
        return null;
    }

    private static ScenarioRecorder getScenarioRecorder0(@Nonnull Scenario scenario, @Nonnull Class<?> scenarioClass) {
        try {
            Method getRecorder = ReflectionUtils.findMethod(scenarioClass, METHOD_GET_RECORDER);
            if (getRecorder == null) {
                throw new ScenarioRecorderNotFoundException("Internal integrity violated: " +
                        "ScenarioDescription class doesn't contains " + METHOD_GET_RECORDER + " method");
            }
            ReflectionUtils.makeAccessible(getRecorder);
            Object methodResult = getRecorder.invoke(scenario);
            if (!(methodResult instanceof ScenarioRecorder)) {
                throw new ScenarioRecorderNotFoundException("Internal integrity violated: " +
                        "Method " + METHOD_GET_RECORDER + " returns incorrect object. " +
                        "Expected " + ScenarioRecorder.class.getName() + " object, but actual is " + methodResult
                );
            }
            return (ScenarioRecorder) methodResult;
        } catch (JaLaTException e) {
            throw e;
        } catch (Exception e) {
            throw new ScenarioRecorderNotFoundException(e);
        }
    }

    private Description getJUnitTestDescription(@Nonnull Class<?> testClass) {
        Description suiteDescription = null;
        if (exception == null && recorder != null) {
            suiteDescription = Description.createSuiteDescription(recorder.getScenarioDescription().getName());
            Collection<UseCaseDescription> useCaseDescriptions = recorder.getScenarioDescription().getUseCases();
            for (UseCaseDescription useCaseDescription : useCaseDescriptions) {
                Description testDescription = Description.createTestDescription(testClass, useCaseDescription.getName());
                useCaseDescription.setJUnitDescription(testDescription);
                suiteDescription.addChild(testDescription);
            }
        }
        return suiteDescription;
    }

    public Description getDescription() {
        return jUnitTestDescriptions;
    }

    public void run(@Nonnull RunNotifier notifier) {
        EachTestNotifier testNotifier = new EachTestNotifier(notifier, getDescription());
        if (exception != null) {
            LoggerFactory.getLogger(getClass()).error(exception::getMessage, exception);
            testNotifier.fireTestIgnored();
            return;
        }

        try {
            new ScenarioInvocation(scenario, recorder, notifier).evaluate();
        } catch (AssumptionViolatedException e) {
            testNotifier.addFailedAssumption(e);
        } catch (StoppedByUserException e) {
            throw e;
        } catch (UnhandledException e) {
            testNotifier.addFailure(e.getCause());
        } catch (Throwable e) {
            testNotifier.addFailure(e);
        }
    }
}
