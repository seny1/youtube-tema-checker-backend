package com.elsasen.youtubetemachecker.app.api.exception;

public class ClientByLoginException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Client with %s login doesn't exist";

    public ClientByLoginException(String login) {
        super(String.format(MESSAGE_TEMPLATE, login));
    }
}
