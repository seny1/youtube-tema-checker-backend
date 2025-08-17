package com.elsasen.youtubetemachecker.app.api.exception;

public class WrongPasswordException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Wrong password for client with login %s";

    public WrongPasswordException(String login) {
        super(String.format(MESSAGE_TEMPLATE, login));
    }
}
