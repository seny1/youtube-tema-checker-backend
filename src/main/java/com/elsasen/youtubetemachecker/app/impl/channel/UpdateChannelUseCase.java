package com.elsasen.youtubetemachecker.app.impl.channel;

import com.elsasen.youtubetemachecker.app.api.channel.UpdateChannelInbound;
import com.elsasen.youtubetemachecker.app.impl.channel.dto.UpdateChannelDto;
import com.elsasen.youtubetemachecker.domain.Channel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateChannelUseCase implements UpdateChannelInbound {
    private final ChannelService channelService;

    @Override
    @Transactional
    public void execute(String clientId, String channelUuid, UpdateChannelDto updateChannelDto) {
        Channel channel = channelService.getChannelWithClientCheck(clientId, channelUuid);

        updateChannel(channel, updateChannelDto);
    }

    // =================================================================================================================
    // Implementation
    // =================================================================================================================

    private void updateChannel(Channel channel, UpdateChannelDto updateChannelDto) {
        if (updateChannelDto.getLink() != null && !channel.getLink().equals(updateChannelDto.getLink())) {
            channelService.checkUniqueChannel(updateChannelDto.getLink());
            log.info("Link of {} channel is updating. {} -> {}", channel.getUuid(), channel.getLink(), updateChannelDto.getLink());
            channel.setLink(updateChannelDto.getLink());
        }
        if (updateChannelDto.getTitle() != null && !channel.getTitle().equals(updateChannelDto.getTitle())) {
            log.info("Title of {} channel is updating. {} -> {}", channel.getUuid(), channel.getTitle(), updateChannelDto.getTitle());
            channel.setTitle(updateChannelDto.getTitle());
        }
        if (updateChannelDto.getThematic() != null && !channel.getThematic().equals(updateChannelDto.getThematic())) {
            log.info("Thematic of {} channel is updating. {} -> {}", channel.getUuid(), channel.getThematic(), updateChannelDto.getThematic());
            channel.setThematic(updateChannelDto.getThematic());
        }
    }
}
