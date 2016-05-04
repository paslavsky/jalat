package com.jalat.config;

import com.jalat.error.IncorrectConfigurationException;
import com.jalat.report.ReportFormat;

import java.nio.file.Path;

/**
 * JaLaT configuration
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
public final class JaLaTConfiguration {
    private final boolean writeScenario;
    private final Path scenarioOutputPath;
    private final ReportFormat reportFormat;

    JaLaTConfiguration(boolean writeScenario, Path scenarioOutputPath, ReportFormat reportFormat) {
        this.writeScenario = writeScenario;
        this.reportFormat = reportFormat;

        if (writeScenario && scenarioOutputPath == null) {
            throw new IncorrectConfigurationException(
                    "\"" + JaLaTParameter.WRITE_SCENARIO.getSystemPropertyName() + "\" is true but \"" +
                            JaLaTParameter.SCENARIO_OUTPUT_DIR.getSystemPropertyName() + "\" is null"
            );
        }
        this.scenarioOutputPath = scenarioOutputPath;
    }

    public boolean isWriteScenario() {
        return writeScenario;
    }

    public Path getScenarioOutputPath() {
        return scenarioOutputPath;
    }

    public ReportFormat getReportFormat() {
        return reportFormat;
    }
}
