package com.example.converteraudio.service;

import com.example.converteraudio.dto.KompetensiDto;
import com.example.converteraudio.properties.ExternalProperties;
import com.example.converteraudio.util.ParserOpenAiUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openai.client.OpenAIClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalCallService {

    private final RestTemplate restTemplate;
    private final String testUrl;
    private final OpenAIClient openAIClient;
    private final ObjectMapper objectMapper;

    public ExternalCallService(RestTemplate restTemplate, ExternalProperties externalProperties, OpenAIClient openAIClient, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.testUrl = externalProperties.getTestUrl();
        this.openAIClient = openAIClient;
        this.objectMapper = objectMapper;
    }

    public String callTest() {
        try {
            ResponseEntity<String> resp = restTemplate.getForEntity(testUrl, String.class);
            if (resp.getStatusCode().is2xxSuccessful()) {
                return resp.getBody();
            } else {
                throw new RuntimeException("External call failed with status: " + resp.getStatusCode().value());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error calling external service: " + testUrl, e);
        }
    }

    public Response callOpenAIOriginal(String input) {
        try {
            ResponseCreateParams params = ResponseCreateParams.builder()
                    .input(input)
                    .model("gpt-5-nano")
                    .build();
            Response response = openAIClient.responses().create(params);
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error calling OpenAI Responses API", e);
        }
    }

    public KompetensiDto callOpenAI(String input) {
        try {
            ResponseCreateParams params = ResponseCreateParams.builder()
                    .input(input)
                    .model("gpt-5-nano")
                    .build();
            Response response = openAIClient.responses().create(params);

            String textOriginal = ParserOpenAiUtil.getTextOriginal(response);
            if (textOriginal != null) {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(textOriginal, KompetensiDto.class);
            }

            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error calling OpenAI Responses API", e);
        }
    }
}
