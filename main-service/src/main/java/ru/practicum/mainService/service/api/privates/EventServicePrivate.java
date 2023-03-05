package ru.practicum.mainService.service.api.privates;

import org.springframework.stereotype.Repository;
import ru.practicum.mainService.dto.event.*;

import java.util.List;

@Repository
public interface EventServicePrivate {

    List<EventShortDto> getEvents(Long userId, Integer from, Integer size);

    EventFullDto addEvent(Long userId, NewEventDto eventDto);

    EventFullDto getEventByEventId(Long userId, Long eventId);

    EventFullDto patchUserEventById(Long userId, Long eventId, UpdateEventUserRequest eventDto);

    ParticipationRequestDto getEventParticipants(Long userId, Long eventId);

    EventRequestStatusUpdateResult changeRequestStatus(Long userId, Long eventId, EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest);

}
