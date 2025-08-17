package com.elsasen.youtubetemachecker.app.impl.video;

import com.elsasen.youtubetemachecker.app.api.client.repo.ClientRepository;
import com.elsasen.youtubetemachecker.app.api.exception.VideoChannelConflictException;
import com.elsasen.youtubetemachecker.app.api.video.CreateVideoInbound;
import com.elsasen.youtubetemachecker.app.api.video.repo.VideoRepository;
import com.elsasen.youtubetemachecker.app.impl.channel.ChannelService;
import com.elsasen.youtubetemachecker.domain.Channel;
import com.elsasen.youtubetemachecker.domain.Client;
import com.elsasen.youtubetemachecker.domain.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateVideoUseCase implements CreateVideoInbound {
    private final ChannelService channelService;
    private final ClientRepository clientRepository;
    private final VideoRepository videoRepository;
    private final VideoService videoService;

    @Override
    public void execute(Video video, String channelUuid, String clientId) throws Exception {
        Channel channel = channelService.getChannelWithClientCheck(clientId, channelUuid);
        Client clientByClientId = clientRepository.getClientByClientId(Long.parseLong(clientId));

        videoService.checkUniqueVideo(video.getLink());
        boolean isVideoFromChannel = videoService.isVideoFromChannel(video.getLink(), channel.getLink());
        if (!isVideoFromChannel) {
            throw new VideoChannelConflictException(channelUuid, video.getLink());
        }

        video.setClient(clientByClientId);
        video.setChannel(channel);
        video.setUuid(UUID.randomUUID());
        videoRepository.save(video);
    }
}
