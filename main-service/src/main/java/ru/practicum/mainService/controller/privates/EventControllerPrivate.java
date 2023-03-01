package ru.practicum.mainService.controller.privates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainService.dto.event.*;
import ru.practicum.mainService.model.Event;
import ru.practicum.mainService.service.api.EventServicePrivate;

import java.util.List;

@RestController
public class EventControllerPrivate {

    private EventServicePrivate eventService;

    @Autowired
    public EventControllerPrivate(EventServicePrivate eventService) {
        this.eventService = eventService;
    }

    @GetMapping(path = "users/{userId}/events")
    public List<EventShortDto> getEvents(@PathVariable(name = "userId") Long userId, @RequestParam(name = "from", defaultValue = "0") Integer from, @RequestParam(name = "size", defaultValue = "10") Integer size) {
        List<Event> events = eventService.getEvents(userId, from, size);
        return null;
    }

    @PostMapping(path = "users/{userId}/events")
    @Transactional
    public List<EventFullDto> addEvent(@PathVariable(name = "userId") Long userId, @RequestBody NewEventDto newEventDto) {

        return eventService.addEvent(userId);
    }

    @GetMapping(path = "users/{userId}/events/{eventid}")
    public List<EventFullDto> getEventByEventId(@PathVariable(name = "userId") Long userId, @PathVariable(name = "eventId") Long eventId) {
        return eventService.getEventByEventId(userId, eventId);
    }

    @PatchMapping(path = "users/{userId}/events")
    @Transactional
    public List<EventFullDto> patchUserEventById(@PathVariable(name = "userId") Long userId, @PathVariable(name = "eventId") Long eventId, @RequestBody UpdateEventUserRequest updateEventUserRequest) {
        return eventService.patchUserEventById(userId, eventId);
    }

    @GetMapping(path = "users/{userId}/events/{eventid}/requests")
    public List<ParticipationRequestDto> getEventParticipants(@PathVariable(name = "userId") Long userId, @PathVariable(name = "eventId") Long eventId) {
        return eventService.getEventParticipants(userId, eventId);
    }

    @PatchMapping(path = "users/{userId}/events/{eventid}/requests")
    @Transactional
    public EventRequestStatusUpdateResult changeRequestStatus(@PathVariable(name = "userId") Long userId, @PathVariable(name = "eventId") Long eventId, @RequestBody EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {
        return eventService.changeRequestStatus(userId, eventId);
    }

}
