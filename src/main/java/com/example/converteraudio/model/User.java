package com.example.converteraudio.model;

import lombok.Data;

import com.google.cloud.Timestamp;

@Data
public class User {

    private String id;
    private String email;
    private String password; // hashed
    private String role;
    private Timestamp createdAt;
}