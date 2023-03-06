package ru.practicum.mainService.service.api.privates;

import ru.practicum.mainService.dto.event.*;
import ru.practicum.mainService.dto.request.ParticipationRequestDto;

import java.util.List;

public interface EventServicePrivate {

    List<EventShortDto> getEvents(Long userId, Integer from, Integer size);

    EventFullDto addEvent(Long userId, NewEventDto eventDto);

    EventFullDto getEventByEventId(Long userId, Long eventId);

    EventFullDto patchUserEventById(Long userId, Long eventId, UpdateEventUserRequest eventDto);

    List<ParticipationRequestDto> getEventParticipants(Long userId, Long eventId);

    EventRequestStatusUpdateResult changeRequestStatus(Long userId, Long eventId, EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest);

}
