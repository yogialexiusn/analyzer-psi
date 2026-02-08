package com.example.converteraudio.config;

import com.example.converteraudio.properties.FirestoreProperties;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FirestoreConfig {

    private final FirestoreProperties firestoreProperties;

    public FirestoreConfig(FirestoreProperties firestoreProperties) {
        this.firestoreProperties = firestoreProperties;
    }

    @Bean
    public Firestore firestore() throws IOException {

        GoogleCredentials credentials =
                GoogleCredentials.getApplicationDefault();

        // GOOGLE_APPLICATION_CREDENTIALS environment variable must be set
        FirestoreOptions options = FirestoreOptions.newBuilder()
                .setProjectId(firestoreProperties.getProjectId())
                .setCredentials(credentials)
                .setDatabaseId(firestoreProperties.getDatabaseId())
                .build();

        return options.getService();
    }
}
