package com.jalat;

/**
 * Void function interface to implement {@code Use Cases}, {@code Checks}, etc.
 * This function doesn't return anything but can throw exceptions.
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
@PublicAPI
@FunctionalInterface
public interface VoidFunction {
    void evaluate() throws Exception;
}
