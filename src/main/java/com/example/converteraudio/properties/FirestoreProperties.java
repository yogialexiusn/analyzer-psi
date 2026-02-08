package com.example.converteraudio.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "firestore")
public class FirestoreProperties {

    private String projectId;
    private String databaseId;
}
