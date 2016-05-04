package com.jalat.config;

/**
 * This class provides access to the {@link JaLaTConfiguration} instance
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public abstract class JaLaTConfigurationProvider {
    private static JaLaTConfiguration configuration = null;

    private JaLaTConfigurationProvider() {
    }

    public static JaLaTConfiguration getConfiguration() {
        if (configuration == null) {
            configuration = JaLaTConfigurationFactory.createNewInstanceFromSystemProperties();
        }
        return configuration;
    }

    @SuppressWarnings("unused")
    public static void setConfiguration(JaLaTConfiguration configuration) {
        JaLaTConfigurationProvider.configuration = configuration;
    }
}
