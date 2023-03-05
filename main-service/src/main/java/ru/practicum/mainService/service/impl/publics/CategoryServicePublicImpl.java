package ru.practicum.mainService.service.impl.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.mainService.dto.category.CategoryDto;
import ru.practicum.mainService.repository.publics.CategoryRepositoryPublic;
import ru.practicum.mainService.service.api.publics.CategoryServicePublic;

@Service
public class CategoryServicePublicImpl implements CategoryServicePublic {

    private final CategoryRepositoryPublic repository;

    @Autowired
    public CategoryServicePublicImpl(CategoryRepositoryPublic repository) {
        this.repository = repository;
    }

    @Override
    public CategoryDto getCategories(Integer from, Integer size) {
        return null;
    }

    @Override
    public CategoryDto getCategoryById(Long catId) {
        return null;
    }
}
