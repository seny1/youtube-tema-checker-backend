package com.elsasen.youtubetemachecker.app.api.video.repo;

import com.elsasen.youtubetemachecker.domain.Video;

import java.util.List;
import java.util.UUID;

public interface VideoRepository {
    Video save(Video video);

    List<Video> findAll();

    List<Video> findByChannelUuid(UUID channelUuid);

    void delete(Video video);
}
