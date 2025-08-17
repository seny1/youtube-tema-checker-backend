package com.elsasen.youtubetemachecker.adapter.jpa;

import com.elsasen.youtubetemachecker.app.api.client.repo.ClientRepository;
import com.elsasen.youtubetemachecker.app.api.exception.ClientByLoginException;
import com.elsasen.youtubetemachecker.app.api.exception.ClientNotFoundException;
import com.elsasen.youtubetemachecker.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClientJpaRepositoryAdapter implements ClientRepository {
    private final ClientJpaRepository clientJpaRepository;

    @Override
    public Client getClientByClientId(Long clientId) {
        return clientJpaRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));
    }

    @Override
    public Client save(Client client) {
        return clientJpaRepository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return clientJpaRepository.findAll();
    }

    @Override
    public Client findByClientLogin(String login) {
        return clientJpaRepository.findByLogin(login)
                .orElseThrow(() -> new ClientByLoginException(login));
    }
}
