package com.elsasen.youtubetemachecker.app.api.video;

import com.elsasen.youtubetemachecker.domain.Video;

import java.util.List;

public interface GetVideosByChannelUuidInbound {
    List<Video> execute(String clientId, String channelUuid);
}
