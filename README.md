JaLaT — Java Lambda Testing
===========================

This framework allow to describe complex long scenario of test like set of steps and checks. It will be usefull to develop integration 
tests with a strict implementation of the script and dependent steps

## Example
```
public class SimpleScenario extends Scenario {{
    description("This is simple scenario to demonstrate base use-cases");

    useCase("Use-case with checks", () -> {
        check("Simple check", () -> assertEquals(4, 2 + 2));
    });

    useCase("Use-case with steps", () -> {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        step("Let's make some actions", () -> {
            atomicInteger.set(5);
        });

        step("Let's check results of previous actions", () -> {
            assertEquals(5, atomicInteger.get());
        });
    });

    useCase("Use-case with step hierarchy", () -> {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        step("This is parent step", () -> {
            atomicBoolean.set(true);

            step("This is sub-step", () -> {
                assertTrue(atomicBoolean.get());
            });
        });
    });
}}
```

## License 
[Apache License v.2](LICENSE.md)
