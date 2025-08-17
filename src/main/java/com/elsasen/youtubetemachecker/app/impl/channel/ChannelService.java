package com.elsasen.youtubetemachecker.app.impl.channel;

import com.elsasen.youtubetemachecker.app.api.channel.repo.ChannelRepository;
import com.elsasen.youtubetemachecker.app.api.client.repo.ClientRepository;
import com.elsasen.youtubetemachecker.app.api.exception.ChannelLinkNotUniqueException;
import com.elsasen.youtubetemachecker.app.api.exception.ClientChannelConflictException;
import com.elsasen.youtubetemachecker.domain.Channel;
import com.elsasen.youtubetemachecker.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChannelService {
    private final ChannelRepository channelRepository;
    private final ClientRepository clientRepository;

    public Channel getChannelWithClientCheck(String clientId, String channelUuid) {
        Channel channel = channelRepository.findByUuid(UUID.fromString(channelUuid));
        Client client = clientRepository.getClientByClientId(Long.parseLong(clientId));
        if (channel.getClient().equals(client)) {
            return channel;
        } else {
            throw new ClientChannelConflictException(clientId, channel.getUuid().toString());
        }
    }

    public void checkUniqueChannel(String newLink) {
        List<Channel> channels = channelRepository.findAll();
        channels.stream()
                .filter(ch -> ch.getLink().equals(newLink))
                .findFirst()
                .ifPresent(ch -> {
                    throw new ChannelLinkNotUniqueException(newLink);
                });
    }
}
