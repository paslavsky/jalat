package com.jalat.report;

import com.jalat.description.*;

import java.io.IOException;

/**
 * JaLaT report writer
 *
 * @author Andrey Paslavsky (mailto:a.paslavsky@gmail.com)
 * @since 0.1
 */
interface ReportWriter extends AutoCloseable{
    void writeTestHeader(ScenarioDescription description) throws IOException;

    void writeTestFotter(ScenarioDescription description) throws IOException;

    void writePreConditionHeader(PreConditionDescription description) throws IOException;

    void writePreConditionFotter(PreConditionDescription description) throws IOException;

    void writePostConditionHeader(PostConditionDescription description) throws IOException;

    void writePostConditionFotter(PostConditionDescription description) throws IOException;

    void writeUseCaseHeader(UseCaseDescription description) throws IOException;

    void writeUseCaseFotter(UseCaseDescription description) throws IOException;

    void writeStepHeader(StepDescription description) throws IOException;

    void writeStepFotter(StepDescription description) throws IOException;

    void writeCheckHeader(CheckDescription description) throws IOException;

    void writeCheckFotter(CheckDescription description) throws IOException;

    void close() throws IOException;
}
