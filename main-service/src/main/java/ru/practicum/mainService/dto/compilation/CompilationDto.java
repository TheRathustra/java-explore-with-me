package ru.practicum.mainService.dto.compilation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.mainService.dto.event.EventShortDto;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompilationDto {

    private Long id;

    private Set<EventShortDto> events;

    private Boolean pinned;

    private String title;

}
