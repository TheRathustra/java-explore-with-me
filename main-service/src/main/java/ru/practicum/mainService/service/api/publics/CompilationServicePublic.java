package ru.practicum.mainService.service.api.publics;

import ru.practicum.mainService.dto.compilation.CompilationDto;

import java.util.List;

public interface CompilationServicePublic {

    List<CompilationDto> getCompilations(Boolean pinned, Integer from, Integer size);

    CompilationDto getCompilationsById(Long compId);

}
