package com.example.converteraudio.model;

import com.google.cloud.Timestamp;
import lombok.Data;

import java.util.List;

@Data
public class Order {

    private String id;
    private String userId;
    private String status;
    private Double totalAmount;
    private List<String> items;
    private Timestamp createdAt;
}
