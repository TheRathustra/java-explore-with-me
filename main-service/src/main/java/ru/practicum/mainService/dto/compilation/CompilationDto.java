package ru.practicum.mainService.dto.compilation;

import ru.practicum.mainService.dto.event.EventShortDto;

import java.util.Set;

public class CompilationDto {

    private Long id;

    private Set<EventShortDto> events;

    private Boolean pinned;

    private String title;

}
