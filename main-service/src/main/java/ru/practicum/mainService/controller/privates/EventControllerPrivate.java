package ru.practicum.mainService.controller.privates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainService.dto.event.*;
import ru.practicum.mainService.dto.request.ParticipationRequestDto;
import ru.practicum.mainService.service.api.privates.EventServicePrivate;
import ru.practicum.mainService.validator.EventValidator;

import java.util.List;

@RestController
public class EventControllerPrivate {

    private final EventServicePrivate eventService;

    private final EventValidator validator;

    @Autowired
    public EventControllerPrivate(EventServicePrivate eventService, EventValidator validator) {
        this.eventService = eventService;
        this.validator = validator;
    }

    /**
     В случае, если по заданным фильтрам не найдено ни одного события, возвращает пустой список
     */
    @GetMapping(path = "users/{userId}/events")
    public List<EventShortDto> getEvents(@PathVariable(name = "userId") Long userId,
                                         @RequestParam(name = "from", defaultValue = "0") Integer from,
                                         @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return eventService.getEvents(userId, from, size);
    }

    /**
     Дата и время на которые намечено событие не может быть раньше, чем через два часа от текущего момента
     */
    @PostMapping(path = "users/{userId}/events")
    @Transactional
    public ResponseEntity<EventFullDto> addEvent(@PathVariable(name = "userId") Long userId,
                                                @RequestBody NewEventDto newEventDto) {
        validator.validateEvent(newEventDto);
        EventFullDto event = eventService.addEvent(userId, newEventDto);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    /**
     В случае, если события с заданным id не найдено, возвращает статус код 404
     */
    @GetMapping(path = "users/{userId}/events/{eventId}")
    public EventFullDto getEventByEventId(@PathVariable(name = "userId") Long userId,
                                                @PathVariable(name = "eventId") Long eventId) {
        return eventService.getEventByEventId(userId, eventId);
    }

    /**
     Дата и время на которые намечено событие не может быть раньше,
     чем через два часа от текущего момента (Ожидается код ошибки 409)
     */
    @PatchMapping(path = "users/{userId}/events/{eventId}")
    @Transactional
    public EventFullDto patchUserEventById(@PathVariable(name = "userId") Long userId,
                                                 @PathVariable(name = "eventId") Long eventId,
                                                 @RequestBody UpdateEventUserRequest updateEventUserRequest) {
        validator.validateEvent(updateEventUserRequest);
        return eventService.patchUserEventById(userId, eventId, updateEventUserRequest);
    }

    /**
     В случае, если по заданным фильтрам не найдено ни одной заявки, возвращает пустой список
     */
    @GetMapping(path = "users/{userId}/events/{eventId}/requests")
    public List<ParticipationRequestDto> getEventParticipants(@PathVariable(name = "userId") Long userId,
                                                        @PathVariable(name = "eventId") Long eventId) {
        return eventService.getEventParticipants(userId, eventId);
    }

    /**
     Если для события лимит заявок равен 0 или отключена пре-модерация заявок, то подтверждение заявок не требуется
     Нельзя подтвердить заявку, если уже достигнут лимит по заявкам на данное событие (Ожидается код ошибки 409)
     Статус можно изменить только у заявок, находящихся в состоянии ожидания (Ожидается код ошибки 409)
     Если при подтверждении данной заявки, лимит заявок для события исчерпан, то все неподтверждённые заявки
     необходимо отклонить
     */
    @PatchMapping(path = "users/{userId}/events/{eventId}/requests")
    @Transactional
    public EventRequestStatusUpdateResult changeRequestStatus(@PathVariable(name = "userId") Long userId,
                                                              @PathVariable(name = "eventId") Long eventId,
                                                              @RequestBody EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {
        return eventService.changeRequestStatus(userId, eventId, eventRequestStatusUpdateRequest);
    }

}
