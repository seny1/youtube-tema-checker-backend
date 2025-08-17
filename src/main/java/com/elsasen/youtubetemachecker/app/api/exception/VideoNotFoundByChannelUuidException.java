package com.elsasen.youtubetemachecker.app.api.exception;

public class VideoNotFoundByChannelUuidException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Video %s not found by channel %s";

    public VideoNotFoundByChannelUuidException(String videoUuid, String channelUuid) {
        super(String.format(MESSAGE_TEMPLATE, videoUuid, channelUuid));
    }
}
