package com.elsasen.youtubetemachecker.app.api.client.repo;

import com.elsasen.youtubetemachecker.domain.Client;

import java.util.List;

public interface ClientRepository {
    Client getClientByClientId(Long clientId);

    Client save(Client client);

    List<Client> findAll();

    Client findByClientLogin(String login);
}
