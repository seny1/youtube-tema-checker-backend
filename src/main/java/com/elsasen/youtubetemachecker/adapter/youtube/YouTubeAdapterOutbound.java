package com.elsasen.youtubetemachecker.adapter.youtube;

import com.elsasen.youtubetemachecker.adapter.youtube.dto.YouTubeChannelResponse;
import com.elsasen.youtubetemachecker.adapter.youtube.dto.YouTubeVideoResponse;

public interface YouTubeAdapterOutbound {
    YouTubeChannelResponse getChannel(String part, String id, String username, String handle);

    YouTubeVideoResponse getVideo(String part, String videoId);
}
