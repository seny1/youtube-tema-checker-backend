package com.elsasen.youtubetemachecker.adapter.jpa;

import com.elsasen.youtubetemachecker.app.api.channel.repo.ChannelRepository;
import com.elsasen.youtubetemachecker.app.api.exception.ChannelNotFoundException;
import com.elsasen.youtubetemachecker.domain.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ChannelJpaRepositoryAdapter implements ChannelRepository {
    private final ChannelJpaRepository channelJpaRepository;

    @Override
    public Channel save(Channel channel) {
        return channelJpaRepository.save(channel);
    }

    @Override
    public void delete(Channel channel) {
        channelJpaRepository.delete(channel);
    }

    @Override
    public Channel findByUuid(UUID channelUuid) {
        return channelJpaRepository.findByUuid(channelUuid)
                .orElseThrow(() -> new ChannelNotFoundException(channelUuid));
    }

    @Override
    public List<Channel> findByClientId(Long clientId) {
        return channelJpaRepository.findByClientId(clientId);
    }

    @Override
    public List<Channel> findAll() {
        return channelJpaRepository.findAll();
    }
}
