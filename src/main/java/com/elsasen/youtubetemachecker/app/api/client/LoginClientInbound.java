package com.elsasen.youtubetemachecker.app.api.client;

import com.elsasen.youtubetemachecker.domain.Client;

public interface LoginClientInbound {
    Client execute(String login, String password);
}
