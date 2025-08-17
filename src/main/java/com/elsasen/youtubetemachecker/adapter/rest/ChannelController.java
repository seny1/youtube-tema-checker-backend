package com.elsasen.youtubetemachecker.adapter.rest;

import com.elsasen.youtubetemachecker.adapter.rest.dto.CreateChannelDto;
import com.elsasen.youtubetemachecker.adapter.rest.dto.GetChannelDto;
import com.elsasen.youtubetemachecker.app.api.channel.UpdateChannelInbound;
import com.elsasen.youtubetemachecker.app.impl.channel.dto.UpdateChannelDto;
import com.elsasen.youtubetemachecker.adapter.rest.mapper.ChannelDtoMapper;
import com.elsasen.youtubetemachecker.app.api.channel.CreateChannelInbound;
import com.elsasen.youtubetemachecker.app.api.channel.DeleteChannelInbound;
import com.elsasen.youtubetemachecker.app.api.channel.GetChannelByUuidInbound;
import com.elsasen.youtubetemachecker.app.api.channel.GetChannelsByClientInbound;
import com.elsasen.youtubetemachecker.domain.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "https://seny-pnz2003.fvds.ru")
@RestController
@RequestMapping("/api/channels")
@RequiredArgsConstructor
public class ChannelController {
    private final ChannelDtoMapper channelDtoMapper;
    private final CreateChannelInbound createChannelInbound;
    private final DeleteChannelInbound deleteChannelInbound;
    private final GetChannelsByClientInbound getChannelsByClientInbound;
    private final GetChannelByUuidInbound getChannelByUuidInbound;
    private final UpdateChannelInbound updateChannelInbound;

    @PostMapping
    public void createChannel(@RequestBody CreateChannelDto createChannelDto, @RequestHeader String clientId) {
        Channel channel = channelDtoMapper.mapToChannel(createChannelDto);
        createChannelInbound.execute(channel, clientId);
    }

    @DeleteMapping("/{channelUuid}/channel")
    public void deleteChannel(@RequestHeader String clientId, @PathVariable String channelUuid) {
        deleteChannelInbound.execute(clientId, channelUuid);
    }

    @GetMapping
    public List<GetChannelDto> getChannelsByClientId(@RequestHeader String clientId) {
        return getChannelsByClientInbound.execute(clientId).stream()
                .map(channelDtoMapper::mapToGetChannelDto)
                .toList();
    }

    @GetMapping("/{channelUuid}/channel")
    public GetChannelDto getChannel(@RequestHeader String clientId, @PathVariable String channelUuid) {
        return channelDtoMapper.mapToGetChannelDto(getChannelByUuidInbound.getChannelByUuid(clientId, channelUuid));
    }

    @PatchMapping("/{channelUuid}/channel")
    public void updateChannel(@RequestHeader String clientId, @PathVariable String channelUuid, @RequestBody UpdateChannelDto updateChannelDto) {
        updateChannelInbound.execute(clientId, channelUuid, updateChannelDto);
    }
}
