package com.example.converteraudio.dto;

import lombok.Data;

import java.util.List;

@Data
public class KompetensiDto {

    private List<String> strength;
    private List<String> opportunity;
    private List<String> weakness;
    private List<String> threat;

}
