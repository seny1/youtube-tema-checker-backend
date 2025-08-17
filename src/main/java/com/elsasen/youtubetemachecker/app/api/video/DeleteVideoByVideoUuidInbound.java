package com.elsasen.youtubetemachecker.app.api.video;

public interface DeleteVideoByVideoUuidInbound {
    void execute(String videoUuid, String clientId, String channelUuid);
}
