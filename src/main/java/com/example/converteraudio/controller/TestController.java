package com.example.converteraudio.controller;

import com.example.converteraudio.dto.KompetensiDto;
import com.example.converteraudio.dto.OpenAIRequest;
import com.example.converteraudio.dto.OpenAiOriginalResponse;
import com.example.converteraudio.dto.OpenAiResponse;
import com.example.converteraudio.service.ExternalCallService;
import com.openai.models.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    private final ExternalCallService externalCallService;

    public TestController(ExternalCallService externalCallService) {
        this.externalCallService = externalCallService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        String body = externalCallService.callTest();
        return ResponseEntity.ok(body == null ? "" : body);
    }

    @PostMapping("/openai/original")
    public ResponseEntity<OpenAiOriginalResponse> callOpenAIOriginal(@RequestBody OpenAIRequest request) {
        String input = request == null || request.getInput() == null ? "" : request.getInput();
        Response output = externalCallService.callOpenAIOriginal(input);
        return ResponseEntity.ok(new OpenAiOriginalResponse(output));
    }

    @PostMapping("/openai")
    public ResponseEntity<KompetensiDto> callOpenAI(@RequestBody OpenAIRequest request) {
        String input = request == null || request.getInput() == null ? "" : request.getInput();
        KompetensiDto output = externalCallService.callOpenAI(input);
        return ResponseEntity.ok(output);
    }
}
