package com.example.converteraudio.config;

import com.google.cloud.firestore.Firestore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirestoreConnectionTest {

    @Bean
    CommandLineRunner testFirestoreConnection(Firestore firestore) {
        return args -> {
            try {
                firestore.listCollections().iterator().hasNext();
                System.out.println("Firestore CONNECTED successfully!");
            } catch (Exception e) {
                System.err.println("Firestore connection FAILED!");
                e.printStackTrace();
            }
        };
    }
}
