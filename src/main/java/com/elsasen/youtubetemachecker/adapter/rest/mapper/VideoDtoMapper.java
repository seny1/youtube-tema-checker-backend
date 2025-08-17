package com.elsasen.youtubetemachecker.adapter.rest.mapper;

import com.elsasen.youtubetemachecker.adapter.rest.dto.CreateVideoDto;
import com.elsasen.youtubetemachecker.adapter.rest.dto.GetVideoDto;
import com.elsasen.youtubetemachecker.domain.Video;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoDtoMapper {
    Video mapToVideo(CreateVideoDto video);

    GetVideoDto mapToGetVideoDto(Video video);
}
