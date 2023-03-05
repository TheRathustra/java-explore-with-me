package ru.practicum.mainService.service.impl.privates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.mainService.dto.event.*;
import ru.practicum.mainService.repository.privates.EventRepositoryPrivate;
import ru.practicum.mainService.service.api.privates.EventServicePrivate;

import java.util.List;

@Service
public class EventServicePrivateImpl implements EventServicePrivate {

    private final EventRepositoryPrivate repository;

    @Autowired
    public EventServicePrivateImpl(EventRepositoryPrivate repository) {
        this.repository = repository;
    }

    @Override
    public List<EventShortDto> getEvents(Long userId, Integer from, Integer size) {
        return null;
    }

    @Override
    public EventFullDto addEvent(Long userId, NewEventDto eventDto) {
        return null;
    }

    @Override
    public EventFullDto getEventByEventId(Long userId, Long eventId) {
        return null;
    }

    @Override
    public EventFullDto patchUserEventById(Long userId, Long eventId, UpdateEventUserRequest eventDto) {
        return null;
    }

    @Override
    public ParticipationRequestDto getEventParticipants(Long userId, Long eventId) {
        return null;
    }

    @Override
    public EventRequestStatusUpdateResult changeRequestStatus(Long userId, Long eventId, EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {
        return null;
    }

}
