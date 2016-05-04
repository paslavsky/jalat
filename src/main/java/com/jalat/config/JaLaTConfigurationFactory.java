package com.jalat.config;

import com.jalat.error.IncorrectConfigurationException;
import com.jalat.logging.LoggerFactory;
import com.jalat.report.ReportFormat;
import com.jalat.util.PathUtils;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory of the {@link JaLaTConfiguration} objects
 *
 * @author Andrey Paslavsky
 * @since 0.1
 */
class JaLaTConfigurationFactory {
    /**
     * This factory methods creates {@link JaLaTConfiguration} from {@link System#getProperties() system properties}
     * @return new {@link JaLaTConfiguration configuration}
     */
    static @Nonnull JaLaTConfiguration createNewInstanceFromSystemProperties() {
        Map<JaLaTParameter, String> parameters = new HashMap<>();
        copyParameterValue(parameters, JaLaTParameter.WRITE_SCENARIO);
        copyParameterValue(parameters, JaLaTParameter.CLEAN_BEFORE_WRITE);
        copyParameterValue(parameters, JaLaTParameter.SCENARIO_OUTPUT_DIR);
        return createNewInstanceFrom(parameters);
    }

    private static void copyParameterValue(Map<JaLaTParameter, String> toParameters, JaLaTParameter parameter) {
        if (System.getProperties().containsKey(parameter.getSystemPropertyName())) {
            toParameters.put(parameter, System.getProperty(parameter.getSystemPropertyName()));
        }
    }

    /**
     * This factory method create {@link JaLaTConfiguration} from map of parameters
     * @param parameters parameters to create new configuration
     * @return new {@link JaLaTConfiguration configuration}
     */
    @SuppressWarnings("WeakerAccess")
    static @Nonnull JaLaTConfiguration createNewInstanceFrom(@Nonnull Map<JaLaTParameter, String> parameters) {
        boolean writeScenario = Boolean.parseBoolean(getOrDefault(parameters, JaLaTParameter.WRITE_SCENARIO));
        Path scenarioOutputDir = null;
        ReportFormat reportFormat = ReportFormat.MARKDOWN;
        if (writeScenario) {
            try {
                scenarioOutputDir = getScenarioOutputDir(parameters);
                reportFormat = getReportFormat(parameters);
            } catch (Exception e) {
                LoggerFactory.getLogger(JaLaTConfigurationFactory.class).warning(e::getMessage, e);
                writeScenario = false;
            }
        }
        return new JaLaTConfiguration(writeScenario, scenarioOutputDir, reportFormat);
    }

    private static Path getScenarioOutputDir(@Nonnull Map<JaLaTParameter, String> parameters) throws IOException {
        String scenarioOutputPath = getOrDefault(parameters, JaLaTParameter.SCENARIO_OUTPUT_DIR);
        Path path = new File(scenarioOutputPath).toPath();
        if (Files.exists(path)) {
            if (!Files.isDirectory(path)) {
                throw new IncorrectConfigurationException("Value of the \"" +
                        JaLaTParameter.SCENARIO_OUTPUT_DIR.getSystemPropertyName() +
                        "\" should refer to the directory");
            }

            if (!Files.isWritable(path)) {
                throw new IncorrectConfigurationException("Directory \"" + scenarioOutputPath + "\" is not a writable");
            }

            if (Boolean.valueOf(getOrDefault(parameters, JaLaTParameter.CLEAN_BEFORE_WRITE))) {
                PathUtils.recursiveDeleteDirectory(path);
            }
            return path;
        } else {
            return Files.createDirectories(path);
        }
    }

    private static ReportFormat getReportFormat(Map<JaLaTParameter, String> parameters) {
        String reportFormat = getOrDefault(parameters, JaLaTParameter.REPORT_FORMAT);
        for (ReportFormat enumValue : ReportFormat.values()) {
            if (enumValue.name().equalsIgnoreCase(reportFormat)) {
                return enumValue;
            }
        }
        throw new IncorrectConfigurationException("Unknown JaLaT report format: " + reportFormat);
    }

    private static String getOrDefault(Map<JaLaTParameter, String> parameters, JaLaTParameter parameter) {
        return parameters.containsKey(parameter) ? parameters.get(parameter) : parameter.getDefaultValue();
    }
}
