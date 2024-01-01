package com.poc.consumer.service;

import com.poc.consumer.config.KafkaProperties;
import com.poc.consumer.dto.EventResponseDTO;
import com.poc.consumer.util.EventDeserializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final KafkaProperties kafkaProperties;
    private final EventDeserializer eventDeserializer;
    private final EventService eventService;

    @KafkaListener(topics = "#{kafkaProperties.defaultTopic}", groupId = "#{kafkaProperties.groupId}")
    public void consume(String message) {
        try {
            EventResponseDTO event = eventDeserializer.deserializeJson(message);
            log.info("Received DTO event in group '{}': {}", kafkaProperties.getGroupId(), event);
            eventService.saveEvent(event);
        } catch (Exception e) {
            log.info("Received non-DTO message in group '{}': {}", kafkaProperties.getGroupId(), message);
        }
    }
}
