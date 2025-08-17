package com.elsasen.youtubetemachecker.adapter.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateChannelDto {
    private String title;
    private String link;
    private String thematic;
}
