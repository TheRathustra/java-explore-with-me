package ru.practicum.mainService.service.impl.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.practicum.mainService.dto.event.EventMapper;
import ru.practicum.mainService.dto.event.EventShortDto;
import ru.practicum.mainService.dto.event.EventSpecs;
import ru.practicum.mainService.model.Event;
import ru.practicum.mainService.repository.publics.EventRepositoryPublic;
import ru.practicum.mainService.service.api.publics.EventServicePublic;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServicePublicImpl implements EventServicePublic {

    private final EventRepositoryPublic repository;

    @Autowired
    public EventServicePublicImpl(EventRepositoryPublic repository) {
        this.repository = repository;
    }

    @Override
    public List<EventShortDto> getEvents(String text, List<Long> categories,
                                         Boolean paid, String rangeStart,
                                         String rangeEnd, Boolean onlyAvailable,
                                         String sort, Integer from, Integer size) {

        Specification<Event> spec = EventSpecs.byText(text)
                .and(EventSpecs.byCategories(categories))
                .and(EventSpecs.byPaid(paid))
                .and(EventSpecs.byRangeStart(rangeStart))
                .and(EventSpecs.byRangeEnd(rangeEnd))
                .and(EventSpecs.byOnlyAvailable(onlyAvailable));
        int page = from / size;
        Pageable pageRequest = PageRequest.of(page, size);

        List<Event> events = repository.findAll(spec, pageRequest);

        return events.stream().map(EventMapper::entityToEventShortDto).collect(Collectors.toList());
    }

    @Override
    public EventShortDto getEventById(Long eventId) {
        return null;
    }
}
