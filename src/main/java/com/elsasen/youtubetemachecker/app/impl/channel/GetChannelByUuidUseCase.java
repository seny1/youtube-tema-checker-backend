package com.elsasen.youtubetemachecker.app.impl.channel;

import com.elsasen.youtubetemachecker.app.api.channel.GetChannelByUuidInbound;
import com.elsasen.youtubetemachecker.domain.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetChannelByUuidUseCase implements GetChannelByUuidInbound {
    private final ChannelService channelService;

    @Override
    public Channel getChannelByUuid(String clientId, String channelUuid) {
        return channelService.getChannelWithClientCheck(clientId, channelUuid);
    }
}
