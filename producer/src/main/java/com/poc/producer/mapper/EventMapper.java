package com.poc.producer.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.poc.producer.config.ErrorProperties;
import com.poc.producer.dto.ErrorConfig;
import com.poc.producer.dto.EventRequestDTO;
import com.poc.producer.exception.JsonConversionException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EventMapper {

    private final ObjectMapper objectMapper;
    private final ErrorProperties errorProperties;

    public String convertToJson(final EventRequestDTO eventRequestDTO) {
        try {
            return objectMapper.writeValueAsString(eventRequestDTO);
        } catch (JsonProcessingException e) {
            ErrorConfig errorConfig = errorProperties.getConversionJson();
            throw new JsonConversionException(errorConfig.getCode(), errorConfig.getMessage(), e);
        }
    }
}

