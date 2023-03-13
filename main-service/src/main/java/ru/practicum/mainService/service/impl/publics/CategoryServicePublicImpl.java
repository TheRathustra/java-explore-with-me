package ru.practicum.mainService.service.impl.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.mainService.dto.category.CategoryDto;
import ru.practicum.mainService.dto.category.CategoryMapper;
import ru.practicum.mainService.error.exception.category.CategoryNotFoundException;
import ru.practicum.mainService.model.Category;
import ru.practicum.mainService.repository.publics.CategoryRepositoryPublic;
import ru.practicum.mainService.service.api.publics.CategoryServicePublic;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServicePublicImpl implements CategoryServicePublic {

    private final CategoryRepositoryPublic repository;

    @Autowired
    public CategoryServicePublicImpl(CategoryRepositoryPublic repository) {
        this.repository = repository;
    }

    @Override
    public List<CategoryDto> getCategories(Integer from, Integer size) {
        int page = from / size;
        Pageable pageRequest = PageRequest.of(page, size);
        Page<Category> categories = repository.findAll(pageRequest);
        return categories.stream().map(CategoryMapper::entityToCategoryDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long catId) {
        Optional<Category> category = repository.findById(catId);
        if (category.isEmpty()) {
            throw new CategoryNotFoundException("Category with id=" + catId + "was not found");
        }

        return CategoryMapper.entityToCategoryDto(category.get());
    }

}
