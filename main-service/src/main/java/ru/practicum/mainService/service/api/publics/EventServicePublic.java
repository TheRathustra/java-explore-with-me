package ru.practicum.mainService.service.api.publics;

import ru.practicum.mainService.dto.event.EventFullDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EventServicePublic {
    List<EventFullDto> getEvents(String text, List<Long> categories,
                            Boolean paid, String rangeStart, String rangeEnd,
                            Boolean onlyAvailable, String sort, Integer from, Integer size, HttpServletRequest request);

    EventFullDto getEventById(Long eventId, HttpServletRequest request);

}
