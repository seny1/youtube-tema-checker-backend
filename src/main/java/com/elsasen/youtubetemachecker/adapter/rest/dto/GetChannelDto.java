package com.elsasen.youtubetemachecker.adapter.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetChannelDto {
    private UUID uuid;
    private String title;
    private String link;
    private String thematic;
    private List<GetVideoDto> videos;
}
