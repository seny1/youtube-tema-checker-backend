package com.elsasen.youtubetemachecker.app.impl.youtube;

import com.elsasen.youtubetemachecker.adapter.youtube.YouTubeAdapterOutbound;
import com.elsasen.youtubetemachecker.adapter.youtube.YouTubeFeignClient;
import com.elsasen.youtubetemachecker.adapter.youtube.dto.YouTubeChannelResponse;
import com.elsasen.youtubetemachecker.adapter.youtube.dto.YouTubeVideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class YouTubeAdapter implements YouTubeAdapterOutbound {
    private final YouTubeFeignClient youTubeFeignClient;

    @Value("${settings.integrations.youtube.apikey}")
    private String apiKey;

    public YouTubeChannelResponse getChannel(String part, String id, String username, String handle) {
        return youTubeFeignClient.getChannel(part, id, username, handle, apiKey);
    }

    public YouTubeVideoResponse getVideo(String part, String videoId) {
        return youTubeFeignClient.getVideo(part, videoId, apiKey);
    }
}
