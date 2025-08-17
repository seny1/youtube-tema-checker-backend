package com.elsasen.youtubetemachecker.adapter.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientDto {
    private String name;
    private String surname;
    private String nickname;
    private String login;
    private String password;
}
