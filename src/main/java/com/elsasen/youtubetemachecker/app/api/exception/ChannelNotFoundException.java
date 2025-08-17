package com.elsasen.youtubetemachecker.app.api.exception;

import java.util.UUID;

public class ChannelNotFoundException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Channel %s not found";

    public ChannelNotFoundException(UUID channelId) {
        super(String.format(MESSAGE_TEMPLATE, channelId));
    }
}