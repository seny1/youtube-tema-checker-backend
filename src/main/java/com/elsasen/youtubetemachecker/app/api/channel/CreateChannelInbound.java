package com.elsasen.youtubetemachecker.app.api.channel;

import com.elsasen.youtubetemachecker.domain.Channel;

public interface CreateChannelInbound {
    void execute(Channel channel, String clientId);
}
