package com.elsasen.youtubetemachecker.adapter.jpa;

import com.elsasen.youtubetemachecker.domain.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChannelJpaRepository extends JpaRepository<Channel, Long> {
    Optional<Channel> findByUuid(UUID uuid);

    List<Channel> findByClientId(Long clientId);
}
