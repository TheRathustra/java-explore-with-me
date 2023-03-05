package ru.practicum.mainService.controller.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainService.dto.event.EventShortDto;
import ru.practicum.mainService.service.api.publics.EventServicePublic;

import java.util.List;

@RestController
@RequestMapping(path = "/events")
public class EventControllerPublic {

    private final EventServicePublic eventService;

    @Autowired
    public EventControllerPublic(EventServicePublic eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public EventShortDto getEvents(@RequestParam(name = "text") String text,
                                   @RequestParam(name = "categories") List<Long> categories,
                                   @RequestParam(name = "paid") Boolean paid,
                                   @RequestParam(name = "rangeStart") String rangeStart,
                                   @RequestParam(name = "rangeEnd") String rangeEnd,
                                   @RequestParam(name = "onlyAvailable") Boolean onlyAvailable,
                                   @RequestParam(name = "sort") String sort,
                                   @RequestParam(name = "from", defaultValue = "0") Integer from,
                                   @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return null;
    }

    @GetMapping(path = "/{id}")
    public EventShortDto getEventById(@PathVariable(name = "id ") Long id ) {
        return null;
    }

}
