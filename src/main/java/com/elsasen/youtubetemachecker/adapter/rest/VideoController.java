package com.elsasen.youtubetemachecker.adapter.rest;

import com.elsasen.youtubetemachecker.adapter.rest.dto.CreateVideoDto;
import com.elsasen.youtubetemachecker.adapter.rest.dto.GetVideoDto;
import com.elsasen.youtubetemachecker.adapter.rest.mapper.VideoDtoMapper;
import com.elsasen.youtubetemachecker.app.api.video.CreateVideoInbound;
import com.elsasen.youtubetemachecker.app.api.video.DeleteVideoByVideoUuidInbound;
import com.elsasen.youtubetemachecker.app.api.video.GetVideosByChannelUuidInbound;
import com.elsasen.youtubetemachecker.app.impl.video.VideoService;
import com.elsasen.youtubetemachecker.domain.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "https://seny-pnz2003.fvds.ru")
@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {
    private final VideoDtoMapper videoDtoMapper;
    private final CreateVideoInbound createVideoInbound;
    private final GetVideosByChannelUuidInbound getVideosByChannelUuidInbound;
    private final DeleteVideoByVideoUuidInbound deleteVideoByVideoUuidInbound;

    @PostMapping("/{channelUuid}/channel")
    public void createVideo(@RequestBody CreateVideoDto createVideoDto, @PathVariable("channelUuid") String channelUuid, @RequestHeader String clientId) throws Exception {
        Video video = videoDtoMapper.mapToVideo(createVideoDto);
        createVideoInbound.execute(video, channelUuid, clientId);
    }

    @GetMapping("/{channelUuid}/channel")
    public List<GetVideoDto> getVideosByChannelUuid(@PathVariable String channelUuid, @RequestHeader String clientId) {
        return getVideosByChannelUuidInbound.execute(clientId, channelUuid).stream()
                .map(videoDtoMapper::mapToGetVideoDto)
                .toList();
    }

    @DeleteMapping("/{channelUuid}/channel/{videoUuid}/video")
    public void deleteChannel(@RequestHeader String clientId, @PathVariable String channelUuid, @PathVariable String videoUuid) {
        deleteVideoByVideoUuidInbound.execute(videoUuid, clientId, channelUuid);
    }
}
