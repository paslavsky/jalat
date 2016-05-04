package com.jalat.report;

import com.jalat.description.*;

import java.io.IOException;

/**
 * Empty implementation of the {@link ReportWriter}
 *
 * @author Andrey Paslavsky (mailto:a.paslavsky@gmail.com)
 * @since 0.1
 */
final class NoReport implements ReportWriter {
    @Override
    public void writeTestHeader(ScenarioDescription description) throws IOException {}

    @Override
    public void writeTestFotter(ScenarioDescription description) throws IOException {}

    @Override
    public void writePreConditionHeader(PreConditionDescription description) throws IOException {}

    @Override
    public void writePreConditionFotter(PreConditionDescription description) throws IOException {}

    @Override
    public void writePostConditionHeader(PostConditionDescription description) throws IOException {}

    @Override
    public void writePostConditionFotter(PostConditionDescription description) throws IOException {}

    @Override
    public void writeUseCaseHeader(UseCaseDescription description) throws IOException {}

    @Override
    public void writeUseCaseFotter(UseCaseDescription description) throws IOException {}

    @Override
    public void writeStepHeader(StepDescription description) throws IOException {}

    @Override
    public void writeStepFotter(StepDescription description) throws IOException {}

    @Override
    public void writeCheckHeader(CheckDescription description) throws IOException {}

    @Override
    public void writeCheckFotter(CheckDescription description) throws IOException {}

    @Override
    public void close() throws IOException {}
}
