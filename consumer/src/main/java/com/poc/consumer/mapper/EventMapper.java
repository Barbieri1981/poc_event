package com.poc.consumer.mapper;

import com.poc.consumer.dto.EventResponseDTO;
import com.poc.consumer.entity.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public Event toEntity(EventResponseDTO dto) {
        Event event = new Event();
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setStartTime(dto.getStartTime());
        event.setEndTime(dto.getEndTime());
        event.setLocation(dto.getLocation());
        return event;
    }

    public EventResponseDTO toDto(Event event) {
        EventResponseDTO dto = new EventResponseDTO();
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setStartTime(event.getStartTime());
        dto.setEndTime(event.getEndTime());
        dto.setLocation(event.getLocation());
        return dto;
    }
}
