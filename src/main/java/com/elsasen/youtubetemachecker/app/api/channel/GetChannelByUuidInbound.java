package com.elsasen.youtubetemachecker.app.api.channel;

import com.elsasen.youtubetemachecker.domain.Channel;

public interface GetChannelByUuidInbound {
    Channel getChannelByUuid(String clientId, String channelUuid);
}
