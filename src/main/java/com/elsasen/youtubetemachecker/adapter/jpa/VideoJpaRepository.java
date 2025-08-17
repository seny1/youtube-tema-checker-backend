package com.elsasen.youtubetemachecker.adapter.jpa;

import com.elsasen.youtubetemachecker.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VideoJpaRepository extends JpaRepository<Video, Long> {
    List<Video> findByChannelUuid(UUID channelUuid);
}
