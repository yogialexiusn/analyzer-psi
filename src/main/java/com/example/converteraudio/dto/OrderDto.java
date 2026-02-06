package com.example.converteraudio.dto;

import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private String description;
    private double amount;
}
