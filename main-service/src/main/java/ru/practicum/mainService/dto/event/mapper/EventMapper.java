package ru.practicum.mainService.dto.event.mapper;

import ru.practicum.mainService.dto.event.NewEventDto;
import ru.practicum.mainService.model.Event;

public class EventMapper {

    public static Event newEventDtoToInctance(NewEventDto dto) {
        return new Event()
                .setAnnotation(dto.getAnnotation())
                .setCategory()
    }

}
