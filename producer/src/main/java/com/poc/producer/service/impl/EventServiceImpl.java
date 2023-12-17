package com.poc.producer.service.impl;

import com.poc.producer.config.EventProperties;
import com.poc.producer.dto.EventRequestDTO;
import com.poc.producer.mapper.EventMapper;
import com.poc.producer.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final EventProperties eventProperties;
    private final EventMapper eventMapper;

    @Override
    public void sendEvent(final String event) {
        log.info("send event {}", event);
        this.kafkaTemplate.send(this.eventProperties.getDefaultTopic(), event);
    }

    @Override
    public void sendEvent(final EventRequestDTO event) {
        this.kafkaTemplate.send(this.eventProperties.getDefaultTopic(), eventMapper.convertToJson(event));
    }
}


