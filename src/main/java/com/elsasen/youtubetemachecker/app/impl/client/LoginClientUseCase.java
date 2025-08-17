package com.elsasen.youtubetemachecker.app.impl.client;

import com.elsasen.youtubetemachecker.app.api.client.LoginClientInbound;
import com.elsasen.youtubetemachecker.app.api.client.repo.ClientRepository;
import com.elsasen.youtubetemachecker.app.api.exception.WrongPasswordException;
import com.elsasen.youtubetemachecker.domain.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoginClientUseCase implements LoginClientInbound {
    private final ClientRepository clientRepository;

    @Override
    public Client execute(String login, String password) {
        Client client = clientRepository.findByClientLogin(login);

        if (client.getPassword().equals(password)) {
            log.info("Login {} success", login);
            return client;
        } else {
            throw new WrongPasswordException(login);
        }
    }
}
