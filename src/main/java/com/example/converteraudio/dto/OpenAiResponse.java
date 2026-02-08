package com.example.converteraudio.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenAiResponse {

    private List<Output> output;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Output {
        private String type;
        private List<Content> content;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Content {
        private String text;
    }
}
