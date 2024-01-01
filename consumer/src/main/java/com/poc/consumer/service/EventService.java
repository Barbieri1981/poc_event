package com.poc.consumer.service;

import com.poc.consumer.dto.EventResponseDTO;
import java.util.List;

public interface EventService {
    void saveEvent(EventResponseDTO eventDto);
    List<EventResponseDTO> getAllEvents();
}
