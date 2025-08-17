package com.elsasen.youtubetemachecker.app.impl.client;

import com.elsasen.youtubetemachecker.app.api.client.CreateClientInbound;
import com.elsasen.youtubetemachecker.app.api.client.repo.ClientRepository;
import com.elsasen.youtubetemachecker.app.api.exception.NotUniqueClientLoginException;
import com.elsasen.youtubetemachecker.domain.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateClientUseCase implements CreateClientInbound {
    private final ClientRepository clientRepository;

    @Override
    public void execute(Client client) {
        checkUniqueClientLogin(client);
        clientRepository.save(client);
        log.info("Client {} saved", client.getLogin());
    }

    // =================================================================================================================
    // Implementation
    // =================================================================================================================

    private void checkUniqueClientLogin(Client client) {
        clientRepository.findAll().stream()
                .filter(c -> c.getLogin().equals(client.getLogin()))
                .findFirst()
                .ifPresent(c -> {
                    throw new NotUniqueClientLoginException(c.getLogin());
                });
    }
}
