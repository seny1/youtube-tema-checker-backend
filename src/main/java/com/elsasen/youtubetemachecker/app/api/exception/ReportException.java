package com.elsasen.youtubetemachecker.app.api.exception;

public class ReportException extends RuntimeException {
    private static final String MESSAGE = "Some problems with report producing";

    public ReportException() {
        super(MESSAGE);
    }
}
