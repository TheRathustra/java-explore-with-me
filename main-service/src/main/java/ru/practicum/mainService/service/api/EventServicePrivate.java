package ru.practicum.mainService.service.api;

import ru.practicum.mainService.model.Event;

import java.util.List;

public interface EventServicePrivate {

    List<Event> getEvents(Long userId, Integer from, Integer size);

    Event addEvent(Long userId, Event event);

    Event getEventByEventId(Long userId, Long eventId);

    Event patchUserEventById(Long eventId, Event event);

    List<Event> getEventParticipants(Long userId, Long eventId);

    Event changeRequestStatus();

}
