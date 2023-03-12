package ru.practicum.mainService.controller.admins;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainService.dto.event.EventFullDto;
import ru.practicum.mainService.dto.event.UpdateEventAdminRequest;
import ru.practicum.mainService.service.api.admins.EventServiceAdmin;
import ru.practicum.mainService.validator.EventValidator;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path = "admin/events")
@Slf4j
public class EventControllerAdmin {

    private final EventServiceAdmin eventService;

    private final EventValidator validator;

    @Autowired
    public EventControllerAdmin(EventServiceAdmin eventService, EventValidator validator) {
        this.eventService = eventService;
        this.validator = validator;
    }

    /**
     Эндпоинт возвращает полную информацию обо всех событиях подходящих под переданные условия
     В случае, если по заданным фильтрам не найдено ни одного события, возвращает пустой список
     */
    @GetMapping
    public List<EventFullDto> getEvents(@RequestParam(name = "users", required = false) List<Long> users,
                                  @RequestParam(name = "states", required = false) List<String> states,
                                  @RequestParam(name = "categories", required = false) List<Long> categories,
                                  @RequestParam(name = "rangeStart", required = false) String rangeStart,
                                  @RequestParam(name = "rangeEnd", required = false) String rangeEnd,
                                  @RequestParam(name = "from", defaultValue = "0") Integer from,
                                  @RequestParam(name = "size", defaultValue = "10") Integer size) {

        log.info("get events users = {} states = {} categories = {} rangeStart = {} rangeEnd = {} from = {} size = {}",
                users, states, categories, rangeStart, rangeEnd, from, size);

        return eventService.getEvents(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    /**
     Редактирование данных любого события администратором. Валидация данных не требуется.
     Обратите внимание:
     дата начала изменяемого события должна быть не ранее чем за час от даты публикации. (Ожидается код ошибки 409)
     событие можно публиковать, только если оно в состоянии ожидания публикации (Ожидается код ошибки 409)
     событие можно отклонить, только если оно еще не опубликовано (Ожидается код ошибки 409)
     */
    @PatchMapping(path = "/{eventId}")
    @Transactional
    public EventFullDto updateEvent(@PathVariable(name = "eventId") Long eventId,
                                    @RequestBody UpdateEventAdminRequest updateEventAdminRequest) {
        validator.validateEvent(updateEventAdminRequest);
        return eventService.updateEvent(eventId, updateEventAdminRequest);
    }

}
