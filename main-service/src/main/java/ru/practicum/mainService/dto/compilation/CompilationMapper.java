package ru.practicum.mainService.dto.compilation;

import ru.practicum.mainService.dto.event.EventMapper;
import ru.practicum.mainService.model.Compilation;

import java.util.stream.Collectors;

public class CompilationMapper {

    public static CompilationDto entityToCompilationDto(Compilation entity) {
        CompilationDto dto = new CompilationDto();
        dto.setId(entity.getId());
        dto.setPinned(entity.getPinned());
        dto.setTitle(entity.getTitle());
        dto.setEvents(entity.getEvents().stream()
                .map(EventMapper::entityToEventShortDto)
                .collect(Collectors.toSet()));
        return dto;
    }

    public static Compilation newCompilationDtoToEntity(NewCompilationDto dto) {
        Compilation compilation = new Compilation();
        compilation.setPinned(dto.getPinned());
        compilation.setTitle(dto.getTitle());

        return compilation;
    }


}
