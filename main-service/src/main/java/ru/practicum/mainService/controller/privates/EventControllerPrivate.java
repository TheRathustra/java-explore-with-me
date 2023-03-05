package ru.practicum.mainService.controller.privates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainService.dto.event.*;
import ru.practicum.mainService.service.api.privates.EventServicePrivate;

import java.util.List;

@RestController
public class EventControllerPrivate {

    private final EventServicePrivate eventService;

    @Autowired
    public EventControllerPrivate(EventServicePrivate eventService) {
        this.eventService = eventService;
    }

    @GetMapping(path = "users/{userId}/events")
    public List<EventShortDto> getEvents(@PathVariable(name = "userId") Long userId,
                                         @RequestParam(name = "from", defaultValue = "0") Integer from,
                                         @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return eventService.getEvents(userId, from, size);
    }

    @PostMapping(path = "users/{userId}/events")
    @Transactional
    public EventFullDto addEvent(@PathVariable(name = "userId") Long userId,
                                       @RequestBody NewEventDto newEventDto) {
        return eventService.addEvent(userId, newEventDto);
    }

    @GetMapping(path = "users/{userId}/events/{eventId}")
    public EventFullDto getEventByEventId(@PathVariable(name = "userId") Long userId,
                                                @PathVariable(name = "eventId") Long eventId) {
        return eventService.getEventByEventId(userId, eventId);
    }

    @PatchMapping(path = "users/{userId}/events/{eventId}")
    @Transactional
    public EventFullDto patchUserEventById(@PathVariable(name = "userId") Long userId,
                                                 @PathVariable(name = "eventId") Long eventId,
                                                 @RequestBody UpdateEventUserRequest updateEventUserRequest) {
        return eventService.patchUserEventById(userId, eventId, updateEventUserRequest);
    }

    @GetMapping(path = "users/{userId}/events/{eventId}/requests")
    public ParticipationRequestDto getEventParticipants(@PathVariable(name = "userId") Long userId,
                                                              @PathVariable(name = "eventId") Long eventId) {
        return eventService.getEventParticipants(userId, eventId);
    }

    @PatchMapping(path = "users/{userId}/events/{eventId}/requests")
    @Transactional
    public EventRequestStatusUpdateResult changeRequestStatus(@PathVariable(name = "userId") Long userId,
                                                              @PathVariable(name = "eventId") Long eventId,
                                                              @RequestBody EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {
        return eventService.changeRequestStatus(userId, eventId, eventRequestStatusUpdateRequest);
    }

}
