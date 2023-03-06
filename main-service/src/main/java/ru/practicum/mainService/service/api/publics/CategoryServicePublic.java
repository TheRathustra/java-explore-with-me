package ru.practicum.mainService.service.api.publics;

import ru.practicum.mainService.dto.category.CategoryDto;

import java.util.List;

public interface CategoryServicePublic {

    List<CategoryDto> getCategories(Integer from, Integer size);

    CategoryDto getCategoryById(Long catId);

}
