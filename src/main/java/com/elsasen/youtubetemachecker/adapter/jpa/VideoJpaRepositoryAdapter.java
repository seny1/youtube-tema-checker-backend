package com.elsasen.youtubetemachecker.adapter.jpa;

import com.elsasen.youtubetemachecker.app.api.video.repo.VideoRepository;
import com.elsasen.youtubetemachecker.domain.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VideoJpaRepositoryAdapter implements VideoRepository {
    private final VideoJpaRepository videoJpaRepository;

    @Override
    public Video save(Video video) {
        return videoJpaRepository.save(video);
    }

    @Override
    public List<Video> findAll() {
        return videoJpaRepository.findAll();
    }

    @Override
    public List<Video> findByChannelUuid(UUID channelUuid) {
        return videoJpaRepository.findByChannelUuid(channelUuid);
    }

    @Override
    public void delete(Video video) {
        videoJpaRepository.delete(video);
    }
}
