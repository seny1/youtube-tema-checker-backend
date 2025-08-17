package com.elsasen.youtubetemachecker.app.impl.video;

import com.elsasen.youtubetemachecker.adapter.youtube.YouTubeAdapterOutbound;
import com.elsasen.youtubetemachecker.app.api.exception.VideoLinkNotUniqueException;
import com.elsasen.youtubetemachecker.app.api.video.repo.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
@RequiredArgsConstructor
public class VideoService {
    private final YouTubeAdapterOutbound youTubeAdapterOutbound;
    private final VideoRepository videoRepository;

    public boolean isVideoFromChannel(String videoLink, String channelLink) throws Exception {
        String videoId = extractVideoId(videoLink);
        String channelIdFromLink = extractChannelId(channelLink);

        if (videoId == null || channelIdFromLink == null) {
            throw new IllegalArgumentException("Не удалось извлечь videoId или channelId из Link");
        }

        var videoResponse = youTubeAdapterOutbound.getVideo("snippet", videoId);
        if (videoResponse.getItems() == null || videoResponse.getItems().isEmpty()) {
            return false;
        }

        String channelIdFromVideo = videoResponse.getItems().get(0).getSnippet().getChannelId();
        return channelIdFromVideo.equals(channelIdFromLink);
    }

    public void checkUniqueVideo(String videoLink) {
        videoRepository.findAll().stream()
                .filter(video -> video.getLink().equals(videoLink))
                .findFirst()
                .ifPresent(video -> {
                    throw new VideoLinkNotUniqueException(videoLink);
                });
    }

    // =================================================================================================================
    // Implementation
    // =================================================================================================================

    private String extractVideoId(String videoLink) throws Exception {
        URI uri = new URI(videoLink);
        String query = uri.getQuery();
        if (query != null && query.contains("v=")) {
            for (String param : query.split("&")) {
                if (param.startsWith("v=")) {
                    return param.substring(2);
                }
            }
        }
        if (uri.getHost().contains("youtu.be")) {
            return uri.getPath().substring(1);
        }
        return null;
    }

    private String extractChannelId(String channelLink) {
        try {
            URI uri = new URI(channelLink);
            String path = uri.getPath();

            String channelId = null;
            String username = null;
            String handle = null;

            if (path.startsWith("/channel/")) {
                channelId = path.substring("/channel/".length());
            } else if (path.startsWith("/c/")) {
                username = path.substring("/c/".length());
            } else if (path.startsWith("/@")) {
                handle = path.substring(1); // оставляем @ в начале
            } else {
                throw new IllegalArgumentException("Неизвестный формат ссылки на канал: " + channelLink);
            }

            var response = youTubeAdapterOutbound.getChannel(
                    "id",
                    channelId,
                    username,
                    handle
            );

            if (response.getItems() != null && !response.getItems().isEmpty()) {
                return response.getItems().get(0).getId();
            } else {
                throw new IllegalStateException("Канал не найден по ссылке: " + channelLink);
            }

        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Некорректная ссылка на канал: " + channelLink, e);
        }
    }
}
