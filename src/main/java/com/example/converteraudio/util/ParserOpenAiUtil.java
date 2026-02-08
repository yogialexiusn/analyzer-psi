package com.example.converteraudio.util;

import com.example.converteraudio.exception.OpenAiParsingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openai.models.responses.Response;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class ParserOpenAiUtil {

    public String getTextOriginal(Response response) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(response);
            JsonNode root = mapper.readTree(jsonString);

            String textValue = "";
            JsonNode textNode = root
                    .path("output")
                    .get(1)
                    .path("content")
                    .get(0)
                    .path("text");

            if (!textNode.isMissingNode()) {
                textValue = textNode.asText();
            }
            log.info("yogi1 = {}", textValue);

            return textValue;

        } catch (Exception e) {
            throw new OpenAiParsingException("Failed to parse OpenAI response: " + e.getMessage());
        }
    }
}
