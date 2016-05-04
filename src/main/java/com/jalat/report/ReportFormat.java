package com.jalat.report;

/**
 * Enum of the supportable report formats
 *
 * @author Andrey Paslavsky (mailto:a.paslavsky@gmail.com)
 * @since 0.0.1
 */
public enum ReportFormat {
    MARKDOWN(".md");

    private final String fileExtension;

    ReportFormat(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileExtension() {
        return fileExtension;
    }
}
