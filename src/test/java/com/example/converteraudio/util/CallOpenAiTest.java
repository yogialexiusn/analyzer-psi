//package com.example.converteraudio.util;
//
//import com.example.converteraudio.dto.KompetensiDto;
//import com.example.converteraudio.properties.ExternalProperties;
//import com.example.converteraudio.service.ExternalCallService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.openai.client.OpenAIClient;
//import com.openai.models.responses.Response;
//import com.openai.models.responses.ResponseCreateParams;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.web.client.RestTemplate;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class CallOpenAiTest {
//
//    @Mock
//    RestTemplate restTemplate;
//
//    @Mock
//    ExternalProperties externalProperties;
//
//    @Mock
//    OpenAIClient openAIClient;
//
//    ObjectMapper objectMapper = new ObjectMapper();
//
//    @Test
//    void callOpenAI_returnsKompetensiDto_whenParserReturnsJson() throws Exception {
//        // Arrange
//        when(externalProperties.getTestUrl()).thenReturn("http://example");
//        ExternalCallService service = new ExternalCallService(restTemplate, externalProperties, openAIClient, objectMapper);
//
//        Response mockResponse = mock(Response.class);
//        // stub response.toString() with any content (ParserOpenAiUtil is mocked below)
//        when(mockResponse.toString()).thenReturn("irrelevant");
//
//        // mock the responses() chain - create a mock for the inner responses API and wire it
//        var responsesApi = mock(Object.class); // type depends on OpenAI SDK; cast below when stubbing openAIClient
//        // safe stubbing: use Mockito to return our mock for responses()
//        when(openAIClient.responses()).thenReturn((com.openai.client.api.ResponsesApi) responsesApi);
//        // stub create(...) on the responses api
//        when(((com.openai.client.api.ResponsesApi) responsesApi).create(any(ResponseCreateParams.class))).thenReturn(mockResponse);
//
//        // Prepare expected DTO and JSON string
//        KompetensiDto expected = new KompetensiDto();
//        // set expected fields on expected as needed
//        String json = objectMapper.writeValueAsString(expected);
//
//        try (var utilities = Mockito.mockStatic(ParserOpenAiUtil.class)) {
//            utilities.when(() -> ParserOpenAiUtil.getTextOriginal(anyString(), any(ObjectMapper.class))).thenReturn(json);
//
//            // Act
//            KompetensiDto actual = service.callOpenAI("input text");
//
//            // Assert
//            assertNotNull(actual);
//            assertEquals(objectMapper.writeValueAsString(expected), objectMapper.writeValueAsString(actual));
//        }
//    }
//
//    @Test
//    void callOpenAI_returnsNull_whenParserReturnsNull() throws Exception {
//        // Arrange
//        when(externalProperties.getTestUrl()).thenReturn("http://example");
//        ExternalCallService service = new ExternalCallService(restTemplate, externalProperties, openAIClient, objectMapper);
//
//        Response mockResponse = mock(Response.class);
//        when(mockResponse.toString()).thenReturn("irrelevant");
//
//        var responsesApi = mock(Object.class);
//        when(openAIClient.responses()).thenReturn((com.openai.client.api.ResponsesApi) responsesApi);
//        when(((com.openai.client.api.ResponsesApi) responsesApi).create(any(ResponseCreateParams.class))).thenReturn(mockResponse);
//
//        try (var utilities = Mockito.mockStatic(ParserOpenAiUtil.class)) {
//            utilities.when(() -> ParserOpenAiUtil.getTextOriginal(anyString(), any(ObjectMapper.class))).thenReturn(null);
//
//            // Act
//            KompetensiDto actual = service.callOpenAI("input text");
//
//            // Assert
//            assertNull(actual);
//        }
//    }
//}