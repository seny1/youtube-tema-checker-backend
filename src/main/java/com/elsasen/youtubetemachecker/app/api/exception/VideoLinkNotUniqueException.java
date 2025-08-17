package com.elsasen.youtubetemachecker.app.api.exception;

public class VideoLinkNotUniqueException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Video's link %s not unique";

    public VideoLinkNotUniqueException(String link) {
        super(String.format(MESSAGE_TEMPLATE, link));
    }
}
