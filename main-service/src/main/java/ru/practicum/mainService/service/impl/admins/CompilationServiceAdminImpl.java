package ru.practicum.mainService.service.impl.admins;

import org.springframework.stereotype.Service;
import ru.practicum.mainService.dto.compilation.CompilationDto;
import ru.practicum.mainService.dto.compilation.NewCompilationDto;
import ru.practicum.mainService.dto.compilation.UpdateCompilationRequest;
import ru.practicum.mainService.service.api.admins.CompilationServiceAdmin;

@Service
public class CompilationServiceAdminImpl implements CompilationServiceAdmin {

    @Override
    public CompilationDto saveCompilation(NewCompilationDto newCompilationDto) {
        return null;
    }

    @Override
    public void deleteCompilation(Long compId) {

    }

    @Override
    public CompilationDto updateCompilation(Long compId, UpdateCompilationRequest updateCompilationRequest) {
        return null;
    }
}
