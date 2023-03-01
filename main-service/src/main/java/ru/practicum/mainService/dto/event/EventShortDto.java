package ru.practicum.mainService.dto.event;

import ru.practicum.mainService.dto.category.CategoryDto;
import ru.practicum.mainService.dto.user.UserShortDto;

public class EventShortDto {

    private Long id;

    private String annotation;

    private CategoryDto category;

    private Integer confirmedRequests;

    private String eventDate;

    private UserShortDto initiator;

    private Boolean paid;

    private String title;

    private Integer views;

}
