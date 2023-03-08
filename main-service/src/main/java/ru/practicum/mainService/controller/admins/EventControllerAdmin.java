package ru.practicum.mainService.controller.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainService.dto.event.EventFullDto;
import ru.practicum.mainService.dto.event.UpdateEventAdminRequest;
import ru.practicum.mainService.service.api.admins.EventServiceAdmin;

import java.util.List;

@RestController
@RequestMapping(path = "admin/events")
public class EventControllerAdmin {

    private final EventServiceAdmin eventService;

    @Autowired
    public EventControllerAdmin(EventServiceAdmin eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventFullDto> getEvents(@RequestParam(name = "users", required = false) List<Long> users,
                                  @RequestParam(name = "states", required = false) List<String> states,
                                  @RequestParam(name = "categories", required = false) List<Long> categories,
                                  @RequestParam(name = "rangeStart", required = false) String rangeStart,
                                  @RequestParam(name = "rangeEnd", required = false) String rangeEnd,
                                  @RequestParam(name = "from", defaultValue = "0") Integer from,
                                  @RequestParam(name = "size", defaultValue = "10") Integer size) {
        //Эндпоинт возвращает полную информацию обо всех событиях подходящих под переданные условия
        //В случае, если по заданным фильтрам не найдено ни одного события, возвращает пустой список
        return eventService.getEvents(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PatchMapping(path = "/{eventId}")
    public EventFullDto updateEvent(@PathVariable(name = "eventId") Long eventId,
                                    @RequestBody UpdateEventAdminRequest updateEventAdminRequest) {
        /*Редактирование данных любого события администратором. Валидация данных не требуется. Обратите внимание:
        дата начала изменяемого события должна быть не ранее чем за час от даты публикации. (Ожидается код ошибки 409)
        событие можно публиковать, только если оно в состоянии ожидания публикации (Ожидается код ошибки 409)
        событие можно отклонить, только если оно еще не опубликовано (Ожидается код ошибки 409)
         */
        return eventService.updateEvent(eventId, updateEventAdminRequest);
    }

}
