package com.jalat.error;

import javax.annotation.Nonnull;

/**
 * {@link ExecutionContextAbsentException} will be thrown when
 * method {@link com.jalat.execution.ExecutionContext#getExecutionContext(Object)} can find
 * {@link com.jalat.execution.ExecutionContext} for given object.
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public class ExecutionContextAbsentException extends JaLaTException {
    @Nonnull
    private final Object obj;

    public ExecutionContextAbsentException(@Nonnull Object obj) {
        super("Can find ExecutionContext for object " + obj);
        this.obj = obj;
    }

    @Nonnull
    public Object getUndefinedObject() {
        return obj;
    }
}
