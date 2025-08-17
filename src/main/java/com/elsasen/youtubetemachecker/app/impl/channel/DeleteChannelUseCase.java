package com.elsasen.youtubetemachecker.app.impl.channel;

import com.elsasen.youtubetemachecker.app.api.channel.DeleteChannelInbound;
import com.elsasen.youtubetemachecker.app.api.channel.repo.ChannelRepository;
import com.elsasen.youtubetemachecker.domain.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteChannelUseCase implements DeleteChannelInbound {
    private final ChannelService channelService;
    private final ChannelRepository channelRepository;

    @Override
    public void execute(String clientId, String channelUuid) {
        Channel channel = channelService.getChannelWithClientCheck(clientId, channelUuid);
        channelRepository.delete(channel);
        log.info("Deleted channel: {} by {} user", channel.getUuid(), clientId);
    }
}
