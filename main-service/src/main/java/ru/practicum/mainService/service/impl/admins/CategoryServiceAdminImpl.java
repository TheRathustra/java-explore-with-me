package ru.practicum.mainService.service.impl.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.mainService.dto.category.CategoryDto;
import ru.practicum.mainService.dto.category.CategoryMapper;
import ru.practicum.mainService.dto.category.NewCategoryDto;
import ru.practicum.mainService.model.Category;
import ru.practicum.mainService.repository.admins.CategoryRepositoryAdmin;
import ru.practicum.mainService.service.api.admins.CategoryServiceAdmin;

@Service
public class CategoryServiceAdminImpl implements CategoryServiceAdmin {

    private final CategoryRepositoryAdmin repository;

    @Autowired
    public CategoryServiceAdminImpl(CategoryRepositoryAdmin repository) {
        this.repository = repository;
    }

    @Override
    public CategoryDto addCategory(NewCategoryDto newCategoryDto) {
        Category category = new Category();
        category.setName(newCategoryDto.getName());
        Category savedCategory = repository.save(category);
        return CategoryMapper.entityToCategoryDto(savedCategory);
    }
}
