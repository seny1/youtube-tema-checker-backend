package com.elsasen.youtubetemachecker.adapter.youtube;

import com.elsasen.youtubetemachecker.adapter.youtube.dto.YouTubeChannelResponse;
import com.elsasen.youtubetemachecker.adapter.youtube.dto.YouTubeVideoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "youtubeApiClient", url = "https://www.googleapis.com/youtube/v3")
public interface YouTubeFeignClient {
    @GetMapping("/videos")
    YouTubeVideoResponse getVideo(
            @RequestParam("part") String part,
            @RequestParam("id") String videoId,
            @RequestParam("key") String apiKey
    );

    @GetMapping("/channels")
    YouTubeChannelResponse getChannel(
            @RequestParam("part") String part,
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "forUsername", required = false) String username,
            @RequestParam(value = "forHandle", required = false) String handle,
            @RequestParam("key") String apiKey
    );
}
