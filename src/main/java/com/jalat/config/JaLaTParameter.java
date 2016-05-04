package com.jalat.config;

import com.jalat.report.ReportFormat;

import java.util.function.Supplier;

/**
 * Enumeration of the JaLaT parameters
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
enum JaLaTParameter {
    WRITE_SCENARIO("writeScenario", Defaults::TRUE),
    SCENARIO_OUTPUT_DIR("scenario.outputDir", Defaults::SCENARIO_OUTPUT_DIR),
    CLEAN_BEFORE_WRITE("cleanBeforeRun", Defaults::TRUE),
    REPORT_FORMAT("reportFormat", Defaults::MARKDOWN_REPORT)
    ;

    private final String systemPropertyName;
    private final Supplier<String> defaultValueSupplier;

    JaLaTParameter(String systemPropertyName, Supplier<String> defaultValueSupplier) {
        this.systemPropertyName = systemPropertyName;
        this.defaultValueSupplier = defaultValueSupplier;
    }

    public String getSystemPropertyName() {
        return systemPropertyName;
    }

    public String getDefaultValue() {
        return defaultValueSupplier.get();
    }

    private static class Defaults {
        static String TRUE() {
            return "true";
        }

        static String SCENARIO_OUTPUT_DIR() {
            return System.getProperty("user.dir") + System.getProperty("file.separator") + "scenario";
        }

        static String MARKDOWN_REPORT() {
            return ReportFormat.MARKDOWN.name();
        }
    }
}
