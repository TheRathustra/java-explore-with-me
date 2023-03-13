package ru.practicum.mainService.controller.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainService.dto.event.EventFullDto;
import ru.practicum.mainService.service.api.publics.EventServicePublic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path = "/events")
public class EventControllerPublic {

    private final EventServicePublic eventService;

    @Autowired
    public EventControllerPublic(EventServicePublic eventService) {
        this.eventService = eventService;
    }

    /**
     Это публичный эндпоинт, соответственно в выдаче должны быть только опубликованные события
     Текстовый поиск (по аннотации и подробному описанию) должен быть без учета регистра букв
     Если в запросе не указан диапазон дат [rangeStart-rangeEnd], то нужно выгружать события, которые произойдут позже текущей даты и времени
     Информация о каждом событии должна включать в себя количество просмотров и количество уже одобренных заявок на участие
     Информацию о том, что по этому эндпоинту был осуществлен и обработан запрос, нужно сохранить в сервисе статистики
     */
    @GetMapping
    public List<EventFullDto> getEvents(@RequestParam(name = "text", required = false) String text,
                                   @RequestParam(name = "categories", required = false) List<Long> categories,
                                   @RequestParam(name = "paid", required = false) Boolean paid,
                                   @RequestParam(name = "rangeStart", required = false) String rangeStart,
                                   @RequestParam(name = "rangeEnd", required = false) String rangeEnd,
                                   @RequestParam(name = "onlyAvailable", required = false) Boolean onlyAvailable,
                                   @RequestParam(name = "sort", required = false) String sort,
                                   @RequestParam(name = "from", defaultValue = "0") Integer from,
                                   @RequestParam(name = "size", defaultValue = "10") Integer size,
                                         HttpServletRequest request) {
        return eventService.getEvents(text, categories, paid,rangeStart, rangeEnd, onlyAvailable, sort, from, size, request);
    }

    /**
     Событие должно быть опубликовано
     Информация о событии должна включать в себя количество просмотров и количество подтвержденных запросов
     Информацию о том, что по этому эндпоинту был осуществлен и обработан запрос, нужно сохранить в сервисе
     статистики
     */
    @GetMapping(path = "/{id}")
    public EventFullDto getEventById(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        return eventService.getEventById(id, request);
    }

}
