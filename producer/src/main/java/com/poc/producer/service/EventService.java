package com.poc.producer.service;

import com.poc.producer.dto.EventRequestDTO;

public interface EventService {
    void sendEvent(final String event);
    void sendEvent(final EventRequestDTO event);
}
