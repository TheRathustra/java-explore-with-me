package ru.practicum.mainService.validator;

import org.springframework.stereotype.Component;
import ru.practicum.mainService.dto.category.CategoryDto;
import ru.practicum.mainService.dto.category.NewCategoryDto;
import ru.practicum.mainService.error.exception.category.CategoryValidationException;

@Component
public class CategoryValidator {

    public void validateCategory(CategoryDto dto) {
        if (dto.getName().isEmpty() || dto.getName().isBlank()) {
            throw new CategoryValidationException("Field: name. Error: must not be blank. Value: null");
        }
    }

    public void validateCategory(NewCategoryDto dto) {
        if (dto.getName().isEmpty() || dto.getName().isBlank()) {
            throw new CategoryValidationException("Field: name. Error: must not be blank. Value: null");
        }
    }

}
