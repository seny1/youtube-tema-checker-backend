package com.elsasen.youtubetemachecker.app.api.channel;

import com.elsasen.youtubetemachecker.app.impl.channel.dto.UpdateChannelDto;

public interface UpdateChannelInbound {
    void execute(String clientId, String channelUuid, UpdateChannelDto updateChannelDto);
}
