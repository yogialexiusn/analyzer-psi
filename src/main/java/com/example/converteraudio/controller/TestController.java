package com.example.converteraudio.controller;

import com.example.converteraudio.dto.*;
import com.example.converteraudio.service.ExternalCallService;
import com.openai.models.responses.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api")
@Slf4j
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


    @PostMapping(value = "/openai/upload-pdf", consumes = "multipart/form-data")
    public ResponseEntity<KompetensiDto> callOpenAIForPdf(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("sampesini");
//        KompetensiDto output = externalCallService.callOpenAIForPdf(file);
        return ResponseEntity.ok(null);
    }

    @PostMapping(value = "/upload-pdf", consumes = "multipart/form-data")
    public ResponseEntity<FileResponse> uploadPdf(@RequestParam("file") MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        String filename = file.getOriginalFilename();
        String base64Encoded = Base64.getEncoder()
                .encodeToString(file.getBytes());

        return ResponseEntity.ok(
                new FileResponse(filename, base64Encoded)
        );
    }
}
