package ru.practicum.mainService.validator;

import org.springframework.stereotype.Component;
import ru.practicum.mainService.dto.compilation.NewCompilationDto;
import ru.practicum.mainService.error.exception.compilation.InvalidCompilationException;

@Component
public class CompilationValidator {

    public void validateCompilation(NewCompilationDto dto) {
        if (dto.getTitle() == null || dto.getTitle().isEmpty() || dto.getTitle().isBlank()) {
            throw new InvalidCompilationException("Field: name. Error: must not be blank. Value: null");
        }
    }

}
