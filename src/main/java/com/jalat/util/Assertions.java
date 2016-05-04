package com.jalat.util;

import com.jalat.PublicAPI;
import com.jalat.VoidFunction;
import com.jalat.execution.ExecutionContext;
import com.jalat.logging.LoggerFactory;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.internal.ArrayComparisonFailure;

/**
 * Interface with default assertions. All methods is a delegate to the {@code JUnit} class {@link Assert}. In case
 * if in method API is absent then {@code JaLaT} will take message from
 * {@link ExecutionContext#getShortDescriptionForCurrentAction()}
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
@PublicAPI
@SuppressWarnings("unused")
public interface Assertions {
    /**
     * For more information please see {@link Assert#assertTrue(String, boolean)}
     */
    default void assertTrue(String message, boolean condition) {
        Assert.assertTrue(message, condition);
    }

    /**
     * For more information please see {@link Assert#assertTrue(String, boolean)}. As an message will be used value
     * from {@link ExecutionContext@getShortDescriptionForCurrentAction}
     */
    default void assertTrue(boolean condition) {
        Assert.assertTrue(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), condition);
    }

    default void assertFalse(String message, boolean condition) {
        Assert.assertFalse(message, condition);
    }

    default void assertFalse(boolean condition) {
        Assert.assertFalse(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), condition);
    }

    default void fail(String message) {
        Assert.fail(message);
    }

    default void fail() {
        Assert.fail(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction());
    }

    default void assertEquals(String message, Object expected, Object actual) {
        Assert.assertEquals(message, expected, actual);
    }

    default void assertEquals(Object expected, Object actual) {
        Assert.assertEquals(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), expected, actual);
    }

    default void assertNotEquals(String message, Object unexpected, Object actual) {
        Assert.assertNotEquals(message, unexpected, actual);
    }

    default void assertNotEquals(Object unexpected, Object actual) {
        Assert.assertNotEquals(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), unexpected, actual);
    }

    default void assertNotEquals(String message, long unexpected, long actual) {
        Assert.assertNotEquals(message, unexpected, actual);
    }

    default void assertNotEquals(long unexpected, long actual) {
        Assert.assertNotEquals(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), unexpected, actual);
    }

    default void assertNotEquals(String message, double unexpected, double actual, double delta) {
        Assert.assertNotEquals(message, unexpected, actual, delta);
    }

    default void assertNotEquals(double unexpected, double actual, double delta) {
        Assert.assertNotEquals(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), unexpected, actual, delta);
    }

    default void assertNotEquals(float unexpected, float actual, float delta) {
        Assert.assertNotEquals(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), unexpected, actual, delta);
    }

    default void assertArrayEquals(String message, Object[] expecteds, Object[] actuals) throws ArrayComparisonFailure {
        Assert.assertArrayEquals(message, expecteds, actuals);
    }

    default void assertArrayEquals(Object[] expecteds, Object[] actuals) {
        Assert.assertArrayEquals(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), expecteds, actuals);
    }

    default void assertArrayEquals(String message, boolean[] expecteds, boolean[] actuals) throws ArrayComparisonFailure {
        Assert.assertArrayEquals(message, expecteds, actuals);
    }

    default void assertArrayEquals(boolean[] expecteds, boolean[] actuals) {
        Assert.assertArrayEquals(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), expecteds, actuals);
    }

    default void assertArrayEquals(String message, byte[] expecteds, byte[] actuals) throws ArrayComparisonFailure {
        Assert.assertArrayEquals(message, expecteds, actuals);
    }

    default void assertArrayEquals(byte[] expecteds, byte[] actuals) {
        Assert.assertArrayEquals(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), expecteds, actuals);
    }

    default void assertArrayEquals(String message, char[] expecteds, char[] actuals) throws ArrayComparisonFailure {
        Assert.assertArrayEquals(message, expecteds, actuals);
    }

    default void assertArrayEquals(char[] expecteds, char[] actuals) {
        Assert.assertArrayEquals(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), expecteds, actuals);
    }

    default void assertArrayEquals(String message, short[] expecteds, short[] actuals) throws ArrayComparisonFailure {
        Assert.assertArrayEquals(message, expecteds, actuals);
    }

    default void assertArrayEquals(short[] expecteds, short[] actuals) {
        Assert.assertArrayEquals(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), expecteds, actuals);
    }

    default void assertArrayEquals(String message, int[] expecteds, int[] actuals) throws ArrayComparisonFailure {
        Assert.assertArrayEquals(message, expecteds, actuals);
    }

    default void assertArrayEquals(int[] expecteds, int[] actuals) {
        Assert.assertArrayEquals(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), expecteds, actuals);
    }

    default void assertArrayEquals(String message, long[] expecteds, long[] actuals) throws ArrayComparisonFailure {
        Assert.assertArrayEquals(message, expecteds, actuals);
    }

    default void assertArrayEquals(long[] expecteds, long[] actuals) {
        Assert.assertArrayEquals(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), expecteds, actuals);
    }

    default void assertArrayEquals(String message, double[] expecteds, double[] actuals, double delta) throws ArrayComparisonFailure {
        Assert.assertArrayEquals(message, expecteds, actuals, delta);
    }

    default void assertArrayEquals(double[] expecteds, double[] actuals, double delta) {
        Assert.assertArrayEquals(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), expecteds, actuals, delta);
    }

    default void assertArrayEquals(String message, float[] expecteds, float[] actuals, float delta) throws ArrayComparisonFailure {
        Assert.assertArrayEquals(message, expecteds, actuals, delta);
    }

    default void assertArrayEquals(float[] expecteds, float[] actuals, float delta) {
        Assert.assertArrayEquals(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), expecteds, actuals, delta);
    }

    default void assertEquals(String message, double expected, double actual, double delta) {
        Assert.assertEquals(message, expected, actual, delta);
    }

    default void assertEquals(String message, float expected, float actual, float delta) {
        Assert.assertEquals(message, expected, actual, delta);
    }

    default void assertNotEquals(String message, float unexpected, float actual, float delta) {
        Assert.assertNotEquals(message, unexpected, actual, delta);
    }

    default void assertEquals(long expected, long actual) {
        Assert.assertEquals(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), expected, actual);
    }

    default void assertEquals(String message, long expected, long actual) {
        Assert.assertEquals(message, expected, actual);
    }

    default void assertEquals(double expected, double actual, double delta) {
        Assert.assertEquals(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), expected, actual, delta);
    }

    default void assertEquals(float expected, float actual, float delta) {
        Assert.assertEquals(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), expected, actual, delta);
    }

    default void assertNotNull(String message, Object object) {
        Assert.assertNotNull(message, object);
    }

    default void assertNotNull(Object object) {
        Assert.assertNotNull(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), object);
    }

    default void assertNull(String message, Object object) {
        Assert.assertNull(message, object);
    }

    default void assertNull(Object object) {
        Assert.assertNull(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), object);
    }

    default void assertSame(String message, Object expected, Object actual) {
        Assert.assertSame(message, expected, actual);
    }

    default void assertSame(Object expected, Object actual) {
        Assert.assertSame(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), expected, actual);
    }

    default void assertNotSame(String message, Object unexpected, Object actual) {
        Assert.assertNotSame(message, unexpected, actual);
    }

    default void assertNotSame(Object unexpected, Object actual) {
        Assert.assertNotSame(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), unexpected, actual);
    }

    default <T> void assertThat(T actual, Matcher<? super T> matcher) {
        Assert.assertThat(ExecutionContext.getExecutionContext(this).getShortDescriptionForCurrentAction(), actual, matcher);
    }

    default <T> void assertThat(String reason, T actual, Matcher<? super T> matcher) {
        Assert.assertThat(reason, actual, matcher);
    }

    /**
     * This method allows you to test that some code must throw the expected error. If the code has completed its
     * execution without any errors then the test will fail.
     * @param errorClass Expected error class
     * @param body Code block that should throw expected error
     */
    default void assertThrow(Class<? extends Throwable> errorClass, VoidFunction body) {
        try {
            body.evaluate();
            fail("Expected that " + errorClass.getName() + " should be thrown! But actually there is no error!");
        } catch (AssertionError e) {
            throw e;
        } catch (Throwable e) {
            if (!errorClass.isInstance(e)) {
                String message = "Expected that " + errorClass.getName() + " should be thrown! " +
                        "Actual error class is " + e.getClass().getName();
                LoggerFactory.getLogger(getClass()).warning(() -> message, e);
                fail(message);
            }
        }
    }

    /**
     * Utility method to identify block of code that required additional review or will be developed in feature
     * @param message Message that will be printed to the log
     * @param body block of code (ignored)
     */
    default void todo(String message, VoidFunction body) {
        LoggerFactory.getLogger(getClass()).warning(() ->
                "TODO " + message + "\n\tat " + new Throwable().getStackTrace()[1].toString(), null);
    }
}
