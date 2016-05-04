package com.jalat.logging;

import org.junit.internal.Classes;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Factory class to produce {@link Logger loggers}
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public abstract class LoggerFactory {
    private static final boolean IS_SLF4J_AVAILABLE = isClassInClassPath("org.slf4j.LoggerFactory");
    private static Map<Class<?>, Logger> cache = Collections.synchronizedMap(new WeakHashMap<>());

    private LoggerFactory() {
    }

    private static boolean isClassInClassPath(String className) {
        try {
            Classes.getClass(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Create new {@link Logger} by given class
     * @param aClass This class is used to create a logger
     * @return new {@link Logger logger}
     */
    public static Logger getLogger(Class<?> aClass) {
        return cache.computeIfAbsent(aClass, aClassArg -> {
            if (IS_SLF4J_AVAILABLE) {
                return new Slf4jLogger(aClass);
            } else {
                return new JdkLogger(aClass);
            }
        });
    }
}
