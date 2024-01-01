package com.poc.consumer.service.impl;

import com.poc.consumer.dto.EventResponseDTO;
import com.poc.consumer.entity.Event;
import com.poc.consumer.mapper.EventMapper;
import com.poc.consumer.repository.EventRepository;
import com.poc.consumer.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public void saveEvent(EventResponseDTO eventDto) {
        Event event = eventMapper.toEntity(eventDto);
        log.info("Saving event: {}", eventDto.getTitle());
        eventRepository.save(event);
    }

    @Override
    public List<EventResponseDTO> getAllEvents() {
        log.info("Retrieving all events");
        return eventRepository.findAll().stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }
}
