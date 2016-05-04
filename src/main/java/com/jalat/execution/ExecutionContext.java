package com.jalat.execution;

import com.jalat.PublicAPI;
import com.jalat.VoidFunction;
import com.jalat.error.ExecutionContextAbsentException;
import com.jalat.util.ObjectReference;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Execution context of the test
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
@PublicAPI
public final class ExecutionContext {
    private static final Map<ObjectReference, ExecutionContext> contextMap = new ConcurrentHashMap<>();

    static void bind(@Nonnull Object obj, @Nonnull ExecutionPropertiesLookup lookupMethod) {
        contextMap.put(new ObjectReference(obj), new ExecutionContext(lookupMethod));
    }

    static void drop(@Nonnull Object obj) {
        contextMap.remove(new ObjectReference(obj));
    }

    /**
     * This method trying to identify {@link ExecutionContext} for given object. In case if {@link ExecutionContext}
     * doesn't exist will be thrown {@link ExecutionContextAbsentException}
     * @param obj Object for which need to find {@link ExecutionContext}
     * @return {@link ExecutionContext} for given object
     */
    public static @Nonnull ExecutionContext getExecutionContext(@Nonnull Object obj) {
        ExecutionContext context = contextMap.get(new ObjectReference(obj));
        if (context == null) {
            throw new ExecutionContextAbsentException(obj);
        }
        return context;
    }

    @Nonnull
    private final ExecutionPropertiesLookup lookupMethod;

    private ExecutionContext(@Nonnull ExecutionPropertiesLookup lookupMethod) {
        this.lookupMethod = lookupMethod;
    }

    /**
     * This message return short description of the current action. It can be name of the
     * {@link com.jalat.Scenario#useCase(String, VoidFunction) use-case},
     * {@link com.jalat.Scenario#check(String, VoidFunction) check},
     * {@link com.jalat.Scenario#step(String, VoidFunction) step} or it can be short description of the
     * {@link com.jalat.Scenario#preCondition(String, VoidFunction) pre-condition},
     * {@link com.jalat.Scenario#postCondition(String, VoidFunction) post-condition}
     * @return Short description
     */
    public @Nonnull String getShortDescriptionForCurrentAction() {
        return (String) lookupMethod.lookup(ExecutionProperty.SHORT_DESCRIPTION);
    }
}
