package com.elsasen.youtubetemachecker.app.api.exception;

public class ChannelLinkNotUniqueException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Channel's link %s not unique";

    public ChannelLinkNotUniqueException(String link) {
        super(String.format(MESSAGE_TEMPLATE, link));
    }
}
