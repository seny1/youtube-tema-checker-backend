package com.elsasen.youtubetemachecker.adapter.rest.mapper;

import com.elsasen.youtubetemachecker.adapter.rest.dto.CreateClientDto;
import com.elsasen.youtubetemachecker.adapter.rest.dto.LoginDto;
import com.elsasen.youtubetemachecker.domain.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientDtoMapper {
    Client mapToClient(CreateClientDto createClientDto);

    @Mapping(target = "clientId", source = "id")
    LoginDto mapToLoginDto(Client client);
}
