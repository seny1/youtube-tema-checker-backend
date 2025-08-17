package com.elsasen.youtubetemachecker.adapter.youtube.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeChannelResponse {
    private List<Item> items;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private String id;
    }
}
