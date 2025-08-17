package com.elsasen.youtubetemachecker.adapter.rest;

import com.elsasen.youtubetemachecker.adapter.rest.dto.CreateClientDto;
import com.elsasen.youtubetemachecker.adapter.rest.dto.GetClientDto;
import com.elsasen.youtubetemachecker.adapter.rest.dto.LoginDto;
import com.elsasen.youtubetemachecker.adapter.rest.mapper.ClientDtoMapper;
import com.elsasen.youtubetemachecker.app.api.client.CreateClientInbound;
import com.elsasen.youtubetemachecker.app.api.client.LoginClientInbound;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://seny-pnz2003.fvds.ru")
@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientDtoMapper clientDtoMapper;
    private final CreateClientInbound createClientInbound;
    private final LoginClientInbound loginClientInbound;

    @PostMapping("/register")
    public void register(@RequestBody CreateClientDto createClientDto) {
        createClientInbound.execute(clientDtoMapper.mapToClient(createClientDto));
    }

    @PostMapping("/login")
    public LoginDto login(@RequestBody GetClientDto getClientDto) {
        return clientDtoMapper.mapToLoginDto(
                loginClientInbound.execute(getClientDto.getLogin(), getClientDto.getPassword())
        );
    }
}
