package com.example.converteraudio.dto;

import lombok.Data;

@Data
public class FileResponse {

    public FileResponse(String filename, String base64Encoded) {
        this.filename = filename;
        this.base64Encoded = base64Encoded;
    }

    private String filename;
    private String base64Encoded;
}
