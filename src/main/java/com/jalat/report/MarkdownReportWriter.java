package com.jalat.report;

import com.jalat.description.*;

import java.io.IOException;
import java.io.Writer;

/**
 * Markdown report writer
 *
 * @author Andrey Paslavsky (mailto:a.paslavsky@gmail.com)
 * @since 0.1
 */
class MarkdownReportWriter implements ReportWriter {
    private static final String LS = System.lineSeparator();
    private final Writer writer;
    private int level = 1;

    MarkdownReportWriter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void writeTestHeader(ScenarioDescription description) throws IOException {
        writer.append("Test: ").write(description.getName());
        newLine();
        repeatCharacter(description.getName().length() + 6, '=');
        newLine();
        writeStringDescription(description.getDescription());
        newLine();
    }

    private void repeatCharacter(int times, char c) throws IOException {
        for (int i = 0; i < times; i++) {
            writer.write(c);
        }
    }

    private void writeStringDescription(CharSequence[] stringDescription) throws IOException {
        if (stringDescription != null) {
            for (CharSequence charSequence : stringDescription) {
                writer.write(charSequence.toString());
                newLine();
            }
        }
    }

    private void newLine() throws IOException {
        writer.write(LS);
    }

    @Override
    public void writeTestFotter(ScenarioDescription description) throws IOException {
        if (description.getDuration() > 0) {
            newLine();
            writer.append(" * * * ").append(LS);
            newLine();
            writer.append("**Test duration**: `").append(String.valueOf(description.getDuration())).append(" ms`");
        }
    }

    @Override
    public void writePreConditionHeader(PreConditionDescription description) throws IOException {
        writeHeader(description, "Pre-Condition");
        level++;
    }

    private void writeHeader(BaseDescription description, String blockName) throws IOException {
        repeatCharacter(level + 1, '#');
        writer.append(blockName).append(": ").write(description.getShortDescription());
        newLine();
        writeStringDescription(description.getDescription());
        newLine();
    }

    @Override
    public void writePreConditionFotter(PreConditionDescription description) throws IOException {
        writeDuration(description, "Pre-Condition");
        level--;
    }

    @Override
    public void writePostConditionHeader(PostConditionDescription description) throws IOException {
        writeHeader(description, "Post-Condition");
        level++;
    }

    @Override
    public void writePostConditionFotter(PostConditionDescription description) throws IOException {
        writeDuration(description, "Post-Condition");
        level--;
    }

    @Override
    public void writeUseCaseHeader(UseCaseDescription description) throws IOException {
        writer.append("Use Case: ").write(description.getName());
        newLine();
        repeatCharacter(description.getName().length() + 10, '-');
        newLine();
        writeStringDescription(description.getDescription());
        newLine();
        level++;
    }

    @Override
    public void writeUseCaseFotter(UseCaseDescription description) throws IOException {
        writeDuration(description, "Use case");
        level--;
    }

    private void writeDuration(BaseDescription description, String blockName) throws IOException {
        if (description.getDuration() > 0) {
            writer.append("**").append(blockName).append(" duration**: `").append(String.valueOf(description.getDuration())).append(" ms`");
            newLine();
            newLine();
        }
    }

    @Override
    public void writeStepHeader(StepDescription description) throws IOException {
        writeHeader(description, "Step");
        writeDuration(description, "Step");
        level++;
    }

    @Override
    public void writeStepFotter(StepDescription description) throws IOException {
        level--;
    }

    @Override
    public void writeCheckHeader(CheckDescription description) throws IOException {
        writeHeader(description, "Check");
    }

    @Override
    public void writeCheckFotter(CheckDescription description) throws IOException {
        writeDuration(description, "Check");
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
