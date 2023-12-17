package com.poc.consumer.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.poc.consumer.dto.EventResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventDeserializer {

    private final ObjectMapper objectMapper;

    public EventResponseDTO deserializeJson(String json) {
        try {
            return objectMapper.readValue(json, EventResponseDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al deserializar el JSON", e);
        }
    }
}
