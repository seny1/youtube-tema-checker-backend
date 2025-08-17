package com.elsasen.youtubetemachecker.app.impl.channel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateChannelDto {
    private String title;
    private String link;
    private String thematic;
}
