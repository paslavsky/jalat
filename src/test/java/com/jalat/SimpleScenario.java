package com.jalat;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleScenario extends Scenario {{
    description("This is simple scenario to demonstrate base use-cases");

    useCase("Use-case with checks", () -> {
        Thread.sleep(5);

        check("Simple check", () -> assertEquals(4, 2 + 2));
    });

    useCase("Use-case with steps", () -> {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        step("Let's make some actions", () -> {
            Thread.sleep(5);

            atomicInteger.set(5);
        });

        step("Let's check results of previous actions", () -> {
            Thread.sleep(5);

            assertEquals(5, atomicInteger.get());
        });
    });

    useCase("Use-case with step hierarchy", () -> {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        step("This is parent step", () -> {
            atomicBoolean.set(true);

            step("This is sub-step", () -> {
                Thread.sleep(5);

                assertTrue(atomicBoolean.get());
            });
        });
    });
}}