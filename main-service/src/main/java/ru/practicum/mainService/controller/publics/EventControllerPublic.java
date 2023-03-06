package ru.practicum.mainService.controller.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainService.dto.event.EventShortDto;
import ru.practicum.mainService.service.api.publics.EventServicePublic;

import java.util.List;

@RestController
@RequestMapping(path = "/events")
public class EventControllerPublic {

    private final EventServicePublic eventService;

    @Autowired
    public EventControllerPublic(EventServicePublic eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventShortDto> getEvents(@RequestParam(name = "text") String text,
                                   @RequestParam(name = "categories") List<Long> categories,
                                   @RequestParam(name = "paid") Boolean paid,
                                   @RequestParam(name = "rangeStart") String rangeStart,
                                   @RequestParam(name = "rangeEnd") String rangeEnd,
                                   @RequestParam(name = "onlyAvailable") Boolean onlyAvailable,
                                   @RequestParam(name = "sort") String sort,
                                   @RequestParam(name = "from", defaultValue = "0") Integer from,
                                   @RequestParam(name = "size", defaultValue = "10") Integer size) {
        /*
        это публичный эндпоинт, соответственно в выдаче должны быть только опубликованные события
        текстовый поиск (по аннотации и подробному описанию) должен быть без учета регистра букв
        если в запросе не указан диапазон дат [rangeStart-rangeEnd], то нужно выгружать события, которые произойдут позже текущей даты и времени
        информация о каждом событии должна включать в себя количество просмотров и количество уже одобренных заявок на участие
        информацию о том, что по этому эндпоинту был осуществлен и обработан запрос, нужно сохранить в сервисе статистики
         */
        return eventService.getEvents(text, categories, paid,rangeStart, rangeEnd, onlyAvailable, sort, from, size);
    }

    @GetMapping(path = "/{id}")
    public EventShortDto getEventById(@PathVariable(name = "id") Long id ) {
        /*
        событие должно быть опубликовано
        информация о событии должна включать в себя количество просмотров и количество подтвержденных запросов
        информацию о том, что по этому эндпоинту был осуществлен и обработан запрос, нужно сохранить в сервисе
        статистики
         */
        return eventService.getEventById(id);
    }

}
