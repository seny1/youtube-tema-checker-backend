package com.elsasen.youtubetemachecker.app.impl.video;

import com.elsasen.youtubetemachecker.app.api.video.GetVideosByChannelUuidInbound;
import com.elsasen.youtubetemachecker.app.api.video.repo.VideoRepository;
import com.elsasen.youtubetemachecker.app.impl.channel.ChannelService;
import com.elsasen.youtubetemachecker.domain.Channel;
import com.elsasen.youtubetemachecker.domain.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetVideosByChannelUuidUseCase implements GetVideosByChannelUuidInbound {
    private final VideoRepository videoRepository;
    private final ChannelService channelService;

    @Override
    public List<Video> execute(String clientId, String channelUuid) {
        Channel channel = channelService.getChannelWithClientCheck(clientId, channelUuid);
        return videoRepository.findByChannelUuid(channel.getUuid());
    }
}
