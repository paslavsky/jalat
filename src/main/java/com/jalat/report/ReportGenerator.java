package com.jalat.report;

import com.jalat.Scenario;
import com.jalat.config.JaLaTConfiguration;
import com.jalat.config.JaLaTConfigurationProvider;
import com.jalat.description.*;
import com.jalat.logging.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

/**
 * JaLaT report generator
 *
 * @author Andrey Paslavsky (mailto:a.paslavsky@gmail.com)
 * @since 0.1
 */
public final class ReportGenerator {
    public static void generateReport(Class<? extends Scenario> scenarioClass, ScenarioDescription description) {
        try (ReportWriter reportWriter = getReportWriter(scenarioClass)) {
            generateReport0(reportWriter, description);
        } catch (IOException e) {
            LoggerFactory.getLogger(ReportGenerator.class).error(() -> "Failed to generate report", e);
        }
    }

    private static
    @Nonnull
    ReportWriter getReportWriter(Class<? extends Scenario> scenarioClass) throws IOException {
        JaLaTConfiguration configuration = JaLaTConfigurationProvider.getConfiguration();
        if (configuration.isWriteScenario()) {
            File reportFile = getReportFile(scenarioClass, configuration);
            switch (configuration.getReportFormat()) {
                case MARKDOWN:
                    return new MarkdownReportWriter(new FileWriter(reportFile));
            }
        }
        return new NoReport();
    }

    private static File getReportFile(Class<? extends Scenario> scenarioClass, JaLaTConfiguration configuration) {
        return new File(
                configuration.getScenarioOutputPath().toFile(),
                scenarioClass.getName() + configuration.getReportFormat().getFileExtension()
        );
    }

    private static void generateReport0(ReportWriter writer, ScenarioDescription description) throws IOException {
        writer.writeTestHeader(description);

        if (description.getPreCondition() != null) {
            writePreCondition(writer, description.getPreCondition());
        }

        for (UseCaseDescription useCase : description.getUseCases()) {
            writeUseCase(writer, useCase);
        }

        if (description.getPostCondition() != null) {
            writePostCondition(writer, description.getPostCondition());
        }

        writer.writeTestFotter(description);
    }

    private static void writeUseCase(ReportWriter writer, UseCaseDescription useCase) throws IOException {
        writer.writeUseCaseHeader(useCase);

        if (useCase.getPreCondition() != null) {
            writePreCondition(writer, useCase.getPreCondition());
        }

        writeChecks(writer, useCase.getChecks());
        writeSteps(writer, useCase.getSteps());

        if (useCase.getPostCondition() != null) {
            writePostCondition(writer, useCase.getPostCondition());
        }

        writer.writeUseCaseFotter(useCase);
    }

    private static void writePreCondition(ReportWriter writer, PreConditionDescription preCondition) throws IOException {
        writer.writePreConditionHeader(preCondition);
        writeSteps(writer, preCondition.getSteps());
        writer.writePreConditionFotter(preCondition);
    }

    private static void writeSteps(ReportWriter writer, Collection<StepDescription> steps) throws IOException {
        for (StepDescription step : steps) {
            writer.writeStepHeader(step);
            writeChecks(writer, step.getChecks());
            writeSteps(writer, step.getSteps());
            writer.writeStepFotter(step);
        }
    }

    private static void writeChecks(ReportWriter writer, Collection<CheckDescription> checks) throws IOException {
        for (CheckDescription check : checks) {
            writer.writeCheckHeader(check);
            writer.writeCheckFotter(check);
        }
    }

    private static void writePostCondition(ReportWriter writer, PostConditionDescription postCondition) throws IOException {
        writer.writePostConditionHeader(postCondition);
        writeSteps(writer, postCondition.getSteps());
        writer.writePostConditionFotter(postCondition);
    }
}
