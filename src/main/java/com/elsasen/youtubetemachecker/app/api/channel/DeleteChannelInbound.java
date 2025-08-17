package com.elsasen.youtubetemachecker.app.api.channel;

public interface DeleteChannelInbound {
    void execute(String clientId, String channelUuid);
}
