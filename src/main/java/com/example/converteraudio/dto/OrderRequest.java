package com.example.converteraudio.dto;

import com.google.cloud.Timestamp;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private String userId;
    private String status;
    private Double totalAmount;
    private List<String> items;
    private Timestamp createdAt;
}
