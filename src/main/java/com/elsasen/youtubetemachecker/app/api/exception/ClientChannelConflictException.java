package com.elsasen.youtubetemachecker.app.api.exception;

public class ClientChannelConflictException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Client %s doesn't have access to %s channel";

    public ClientChannelConflictException(String clientId, String channelUuid) {
        super(String.format(MESSAGE_TEMPLATE, clientId, channelUuid));
    }
}
