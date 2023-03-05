package ru.practicum.mainService.dto.event;

import ru.practicum.mainService.dto.category.CategoryMapper;
import ru.practicum.mainService.dto.user.UserMapper;
import ru.practicum.mainService.model.Event;

public class EventMapper {

    public static EventShortDto entityToEventShortDto(Event entity) {
        EventShortDto dto = new EventShortDto();
        dto.setId(entity.getId());
        dto.setAnnotation(entity.getAnnotation());
        dto.setCategory(CategoryMapper.entityToCategoryDto(entity.getCategory()));
        dto.setConfirmedRequests(entity.getConfirmedRequests());
        dto.setEventDate(entity.getEventDate());
        dto.setInitiator(UserMapper.entityToUserShortDto(entity.getInitiator()));
        dto.setPaid(entity.getPaid());
        dto.setTitle(entity.getTitle());
        return dto;
    }


}
