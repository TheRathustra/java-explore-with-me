package ru.practicum.mainService.service.api.admins;

import ru.practicum.mainService.dto.compilation.CompilationDto;
import ru.practicum.mainService.dto.compilation.NewCompilationDto;
import ru.practicum.mainService.dto.compilation.UpdateCompilationRequest;

public interface CompilationServiceAdmin {

    CompilationDto saveCompilation(NewCompilationDto newCompilationDto);

    void deleteCompilation(Long compId);

    CompilationDto updateCompilation(Long compId, UpdateCompilationRequest updateCompilationRequest);

}
