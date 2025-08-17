package com.elsasen.youtubetemachecker.app.api.channel;

import com.elsasen.youtubetemachecker.domain.Channel;

import java.util.List;

public interface GetChannelsByClientInbound {
    List<Channel> execute(String clientId);
}
