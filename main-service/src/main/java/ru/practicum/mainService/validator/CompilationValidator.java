package ru.practicum.mainService.validator;

import org.springframework.stereotype.Component;
import ru.practicum.mainService.dto.compilation.NewCompilationDto;
import ru.practicum.mainService.error.exception.category.CategoryValidationException;

@Component
public class CompilationValidator {

    public void validateCompilation(NewCompilationDto dto) {
        if (dto.getTitle() == null || dto.getTitle().isEmpty() || dto.getTitle().isBlank()) {
            throw new CategoryValidationException("Field: name. Error: must not be blank. Value: null");
        }
    }

}
