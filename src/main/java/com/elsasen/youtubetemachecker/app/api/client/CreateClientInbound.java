package com.elsasen.youtubetemachecker.app.api.client;

import com.elsasen.youtubetemachecker.domain.Client;

public interface CreateClientInbound {
    void execute(Client client);
}
