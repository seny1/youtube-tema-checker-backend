package com.elsasen.youtubetemachecker.app.api.exception;

public class NotUniqueClientLoginException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "The client %s already exists";

    public NotUniqueClientLoginException(String login) {
        super(String.format(MESSAGE_TEMPLATE, login));
    }
}
