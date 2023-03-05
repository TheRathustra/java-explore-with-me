package ru.practicum.mainService.service.api.publics;

import ru.practicum.mainService.dto.category.CategoryDto;

public interface CategoryServicePublic {

    CategoryDto getCategories(Integer from, Integer size);

    CategoryDto getCategoryById(Long catId);

}
