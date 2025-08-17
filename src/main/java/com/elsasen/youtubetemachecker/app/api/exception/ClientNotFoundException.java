package com.elsasen.youtubetemachecker.app.api.exception;

public class ClientNotFoundException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Client %s not found";

    public ClientNotFoundException(Long clientId) {
        super(String.format(MESSAGE_TEMPLATE, clientId));
    }
}
