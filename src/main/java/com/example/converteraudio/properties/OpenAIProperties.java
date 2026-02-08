package com.example.converteraudio.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Data
@ConfigurationProperties(prefix = "openai")
public class OpenAIProperties {

    private String apiKey;
    private Duration timeout = Duration.ofSeconds(30);

    // getter & setter
}
