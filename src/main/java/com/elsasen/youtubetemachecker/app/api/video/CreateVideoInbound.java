package com.elsasen.youtubetemachecker.app.api.video;

import com.elsasen.youtubetemachecker.domain.Video;

public interface CreateVideoInbound {
    void execute(Video video, String channelUuid, String clientId) throws Exception;
}
