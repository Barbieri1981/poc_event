package com.poc.producer.controller;

import com.poc.producer.dto.EventRequestDTO;
import com.poc.producer.service.EventService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping("/publish")
    public String sendEvent(final @RequestParam("event") String event) {
        this.eventService.sendEvent(event);
        return "Sent event: " + event;
    }

    @PostMapping("/events")
    public String publishEvent(@RequestBody EventRequestDTO eventRequestDTO) {
       this.eventService.sendEvent(eventRequestDTO);
        return "Event published";
    }
}
