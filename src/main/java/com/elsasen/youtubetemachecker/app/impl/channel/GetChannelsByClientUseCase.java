package com.elsasen.youtubetemachecker.app.impl.channel;

import com.elsasen.youtubetemachecker.app.api.channel.GetChannelsByClientInbound;
import com.elsasen.youtubetemachecker.app.api.channel.repo.ChannelRepository;
import com.elsasen.youtubetemachecker.domain.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetChannelsByClientUseCase implements GetChannelsByClientInbound {
    private final ChannelRepository channelRepository;

    @Override
    public List<Channel> execute(String clientId) {
        return channelRepository.findByClientId(Long.parseLong(clientId));
    }
}
