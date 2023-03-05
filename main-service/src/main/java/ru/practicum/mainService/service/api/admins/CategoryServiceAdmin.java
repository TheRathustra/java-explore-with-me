package ru.practicum.mainService.service.api.admins;

import ru.practicum.mainService.dto.category.CategoryDto;
import ru.practicum.mainService.dto.category.NewCategoryDto;

public interface CategoryServiceAdmin {

    CategoryDto addCategory(NewCategoryDto newCategoryDto);

}
