package com.elsasen.youtubetemachecker.app.api.channel.repo;

import com.elsasen.youtubetemachecker.domain.Channel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChannelRepository {
    Channel save(Channel channel);

    void delete(Channel channel);

    Channel findByUuid(UUID channelUuid);

    List<Channel> findByClientId(Long clientId);

    List<Channel> findAll();
}
