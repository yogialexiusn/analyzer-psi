package com.example.converteraudio;

import com.example.converteraudio.properties.ExternalProperties;
import com.example.converteraudio.properties.OpenAIProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({OpenAIProperties.class, ExternalProperties.class})
public class ConverterAudioApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConverterAudioApplication.class, args);
    }
}
