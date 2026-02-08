package com.example.converteraudio.dto;

import com.openai.models.responses.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAiOriginalResponse {
    private Response output;
}
