package com.jalat;

import com.jalat.execution.ScenarioRecorder;
import com.jalat.execution.ScenarioRunner;
import com.jalat.logging.Loggable;
import com.jalat.util.Assertions;
import org.junit.runner.RunWith;

import javax.annotation.Nonnull;

/**
 * Scenario base class.
 *
 * TODO detail class description with case of usages
 *
 * @author Andrey Paslavsky
 * @since  0.1
 */
@PublicAPI
@RunWith(ScenarioRunner.class)
public abstract class Scenario implements Loggable, Assertions {
    private final ScenarioRecorder recorder = new ScenarioRecorder();

    /**
     * This constructor define the name as a class name
     */
    protected Scenario() {
        recorder.setName(getClass().getName());
    }

    /**
     * Constructor with custom name
     * @param name Custom name
     */
    protected Scenario(@Nonnull String name) {
        recorder.setName(name);
    }

    /**
     * This method define description. You can use this method inside any other methods to provide more information
     * about your actions.
     * @param description Description
     */
    protected final void description(@Nonnull CharSequence... description) {
        recorder.setDescription(description);
    }

    /**
     * This method define pre-condition
     * @param shortDescription short description of the pre-condition
     * @param impl implementation of the pre-condition
     */
    protected final void preCondition(@Nonnull String shortDescription, @Nonnull VoidFunction impl) {
        recorder.setPreCondition(shortDescription, impl);
    }

    /**
     * This method define post-condition
     * @param shortDescription short description of the post-condition
     * @param impl implementation of the post-condition
     */
    protected final void postCondition(@Nonnull String shortDescription, @Nonnull VoidFunction impl) {
        recorder.setPostCondition(shortDescription, impl);
    }

    /**
     * This method define new {@code Use Case}
     * @param name name of the use case
     * @param impl implementation of the use case
     */
    protected final void useCase(@Nonnull String name, @Nonnull VoidFunction impl) {
        recorder.addUseCase(name, impl);
    }

    /**
     * This method define new {@code step}. You can use this method inside
     * {@link #useCase(String, VoidFunction) use-case},
     * {@link #preCondition(String, VoidFunction) pre-condition},
     * {@link #postCondition(String, VoidFunction) post-condition} or inside
     * {@link #step(String, VoidFunction) another step} to define sub step.
     * @param shortDescription Name of the step
     * @param impl Implementation of the step
     */
    @SuppressWarnings("JavaDoc")
    protected final void step(@Nonnull String shortDescription, @Nonnull VoidFunction impl) throws Exception {
        recorder.addStep(shortDescription, impl);
    }

    /**
     * This method define new {@code check}. You can use this method inside
     * {@link #useCase(String, VoidFunction) use-case},
     * {@link #preCondition(String, VoidFunction) pre-condition},
     * {@link #postCondition(String, VoidFunction) post-condition} and
     * {@link #step(String, VoidFunction) step}.
     * @param shortDescription Name of the check
     * @param impl Implementation of the check
     */
    protected final void check(@Nonnull String shortDescription, @Nonnull VoidFunction impl) {
        recorder.addCheck(shortDescription, impl);
    }

    @SuppressWarnings("unused")
    private ScenarioRecorder getRecorder() {
        return recorder;
    }
}
