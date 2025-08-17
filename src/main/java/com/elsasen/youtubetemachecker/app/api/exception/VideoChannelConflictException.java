package com.elsasen.youtubetemachecker.app.api.exception;

public class VideoChannelConflictException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Channel %s doesn't have %s video";

    public VideoChannelConflictException(String channelUuid, String videoLink) {
        super(String.format(MESSAGE_TEMPLATE, channelUuid, videoLink));
    }
}
