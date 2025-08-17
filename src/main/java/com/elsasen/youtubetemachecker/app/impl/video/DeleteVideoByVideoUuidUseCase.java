package com.elsasen.youtubetemachecker.app.impl.video;

import com.elsasen.youtubetemachecker.app.api.channel.repo.ChannelRepository;
import com.elsasen.youtubetemachecker.app.api.exception.VideoChannelConflictException;
import com.elsasen.youtubetemachecker.app.api.video.DeleteVideoByVideoUuidInbound;
import com.elsasen.youtubetemachecker.app.api.video.repo.VideoRepository;
import com.elsasen.youtubetemachecker.app.impl.channel.ChannelService;
import com.elsasen.youtubetemachecker.domain.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteVideoByVideoUuidUseCase implements DeleteVideoByVideoUuidInbound {
    private final VideoRepository videoRepository;
    private final ChannelService channelService;

    @Override
    public void execute(String videoUuid, String clientId, String channelUuid) {
        Channel channel = channelService.getChannelWithClientCheck(clientId, channelUuid);

        channel.getVideos().stream()
                .filter(video -> video.getUuid().equals(UUID.fromString(videoUuid)))
                .findFirst()
                .ifPresentOrElse(video1 -> {
                    videoRepository.delete(video1);
                    log.info("Deleted video with UUID: {}", videoUuid);
                }, () -> {
                    throw new VideoChannelConflictException(channelUuid, videoUuid);
                });
    }
}
