package com.elsasen.youtubetemachecker.adapter.rest.mapper;

import com.elsasen.youtubetemachecker.adapter.rest.dto.CreateChannelDto;
import com.elsasen.youtubetemachecker.adapter.rest.dto.GetChannelDto;
import com.elsasen.youtubetemachecker.domain.Channel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChannelDtoMapper {
    Channel mapToChannel(CreateChannelDto createChannelDto);

    GetChannelDto mapToGetChannelDto(Channel channel);
}
