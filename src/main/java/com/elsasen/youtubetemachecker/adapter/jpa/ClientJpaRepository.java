package com.elsasen.youtubetemachecker.adapter.jpa;

import com.elsasen.youtubetemachecker.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientJpaRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByLogin(String login);
}
