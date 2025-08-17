package com.elsasen.youtubetemachecker.app.impl.channel;

import com.elsasen.youtubetemachecker.app.api.channel.CreateChannelInbound;
import com.elsasen.youtubetemachecker.app.api.channel.repo.ChannelRepository;
import com.elsasen.youtubetemachecker.app.api.client.repo.ClientRepository;
import com.elsasen.youtubetemachecker.domain.Channel;
import com.elsasen.youtubetemachecker.domain.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateChannelUseCase implements CreateChannelInbound {
    private final ChannelRepository channelRepository;
    private final ClientRepository clientRepository;
    private final ChannelService channelService;

    @Override
    public void execute(Channel channel, String clientId) {
        channelService.checkUniqueChannel(channel.getLink());
        Client client = clientRepository.getClientByClientId(Long.parseLong(clientId));
        channel.setClient(client);
        channel.setUuid(UUID.randomUUID());
        log.info("Create new channel with title {} link {} thematic {} by {} user",
                channel.getTitle(), channel.getLink(), channel.getThematic(), channel.getClient().getId());
        channelRepository.save(channel);
    }
}
