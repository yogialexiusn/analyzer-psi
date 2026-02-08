package com.example.converteraudio.config;


import com.example.converteraudio.properties.OpenAIProperties;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIConfig {

    @Bean
    public OpenAIClient openAIClient(OpenAIProperties properties) {
        String apiKey = properties.getApiKey();
        if (apiKey == null || apiKey.isBlank()) {
            return OpenAIOkHttpClient.fromEnv();
        }

        return OpenAIOkHttpClient.builder()
                .apiKey(apiKey)
                .build();
    }

}
