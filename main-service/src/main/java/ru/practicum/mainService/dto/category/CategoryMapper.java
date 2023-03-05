package ru.practicum.mainService.dto.category;

import ru.practicum.mainService.model.Category;

public class CategoryMapper {

    public static CategoryDto entityToCategoryDto(Category entity) {
        return new CategoryDto(entity.getId(),
                                entity.getName());
    }

}
