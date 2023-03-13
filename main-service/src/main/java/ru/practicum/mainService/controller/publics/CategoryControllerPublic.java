package ru.practicum.mainService.controller.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainService.dto.category.CategoryDto;
import ru.practicum.mainService.service.api.publics.CategoryServicePublic;

import java.util.List;

@RestController
@RequestMapping(path = "/categories")
public class CategoryControllerPublic {

    private final CategoryServicePublic categoryService;

    @Autowired
    public CategoryControllerPublic(CategoryServicePublic categoryService) {
        this.categoryService = categoryService;
    }

    /**
     В случае, если по заданным фильтрам не найдено ни одной категории, возвращает пустой список
     */
    @GetMapping()
    public List<CategoryDto> getCategories(@RequestParam(name = "from", defaultValue = "0") Integer from,
                                           @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return categoryService.getCategories(from, size);
    }

    /**
     В случае, если категории с заданным id не найдено, возвращает статус код 404
     */
    @GetMapping(path = "/{catId}")
    public CategoryDto getCategoryById(@PathVariable(name = "catId") Long catId) {
        return categoryService.getCategoryById(catId);
    }

}
