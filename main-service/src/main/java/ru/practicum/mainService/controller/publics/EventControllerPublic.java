package ru.practicum.mainService.controller.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.client.StatsClient;
import ru.practicum.mainService.dto.event.EventShortDto;
import ru.practicum.mainService.service.api.publics.EventServicePublic;
import ru.practicum.statsDto.dto.HitDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(path = "/events")
public class EventControllerPublic {

    private final EventServicePublic eventService;

    private final StatsClient client;

    @Autowired
    public EventControllerPublic(EventServicePublic eventService, StatsClient client) {
        this.eventService = eventService;
        this.client = client;
    }

    @GetMapping
    public List<EventShortDto> getEvents(@RequestParam(name = "text", required = false) String text,
                                   @RequestParam(name = "categories", required = false) List<Long> categories,
                                   @RequestParam(name = "paid", required = false) Boolean paid,
                                   @RequestParam(name = "rangeStart", required = false) String rangeStart,
                                   @RequestParam(name = "rangeEnd", required = false) String rangeEnd,
                                   @RequestParam(name = "onlyAvailable", required = false) Boolean onlyAvailable,
                                   @RequestParam(name = "sort", required = false) String sort,
                                   @RequestParam(name = "from", defaultValue = "0") Integer from,
                                   @RequestParam(name = "size", defaultValue = "10") Integer size,
                                         HttpServletRequest request) {
        /*
        это публичный эндпоинт, соответственно в выдаче должны быть только опубликованные события
        текстовый поиск (по аннотации и подробному описанию) должен быть без учета регистра букв
        если в запросе не указан диапазон дат [rangeStart-rangeEnd], то нужно выгружать события, которые произойдут позже текущей даты и времени
        информация о каждом событии должна включать в себя количество просмотров и количество уже одобренных заявок на участие
        информацию о том, что по этому эндпоинту был осуществлен и обработан запрос, нужно сохранить в сервисе статистики
         */

        //HitDto hitDto = makeHitDto(request);
        //client.sendHit(hitDto);
        return eventService.getEvents(text, categories, paid,rangeStart, rangeEnd, onlyAvailable, sort, from, size);
    }

    @GetMapping(path = "/{id}")
    public EventShortDto getEventById(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        /*
        событие должно быть опубликовано
        информация о событии должна включать в себя количество просмотров и количество подтвержденных запросов
        информацию о том, что по этому эндпоинту был осуществлен и обработан запрос, нужно сохранить в сервисе
        статистики
         */
        //HitDto hitDto = makeHitDto(request);
        //client.sendHit(hitDto);
        return eventService.getEventById(id);
    }

    private HitDto makeHitDto(HttpServletRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ROOT);

        HitDto hitDto = new HitDto();
        hitDto.setIp(request.getRemoteAddr());
        hitDto.setUri(request.getRequestURI());
        hitDto.setTimestamp(LocalDateTime.now().format(formatter));
        hitDto.setApp("main-service");

        return hitDto;
    }

}
