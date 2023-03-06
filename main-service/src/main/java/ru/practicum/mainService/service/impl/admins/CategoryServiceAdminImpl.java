package ru.practicum.mainService.service.impl.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.mainService.dto.category.CategoryDto;
import ru.practicum.mainService.dto.category.CategoryMapper;
import ru.practicum.mainService.dto.category.NewCategoryDto;
import ru.practicum.mainService.error.exception.category.CategoryIsNotEmptyException;
import ru.practicum.mainService.error.exception.category.CategoryNotFoundException;
import ru.practicum.mainService.error.exception.category.CategoryWithNameExistException;
import ru.practicum.mainService.model.Category;
import ru.practicum.mainService.model.Event;
import ru.practicum.mainService.repository.admins.CategoryRepositoryAdmin;
import ru.practicum.mainService.repository.admins.EventRepositoryAdmin;
import ru.practicum.mainService.service.api.admins.CategoryServiceAdmin;

import java.util.List;

@Service
public class CategoryServiceAdminImpl implements CategoryServiceAdmin {

    private final CategoryRepositoryAdmin repository;

    private final EventRepositoryAdmin eventRepository;

    @Autowired
    public CategoryServiceAdminImpl(CategoryRepositoryAdmin repository, EventRepositoryAdmin eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    @Override
    public CategoryDto addCategory(NewCategoryDto newCategoryDto) {
        List<Category> allByName = repository.findAllByName(newCategoryDto.getName());
        if (!allByName.isEmpty()) {
            throw new CategoryWithNameExistException("Category with name="
                    + newCategoryDto.getName() + " already exists");
        }
        Category category = new Category();
        category.setName(newCategoryDto.getName());
        Category savedCategory = repository.save(category);
        return CategoryMapper.entityToCategoryDto(savedCategory);
    }

    @Override
    public void deleteCategory(Long catId) {
        if (!repository.existsById(catId)) {
            throw new CategoryNotFoundException("Category with id=" + catId + "was not found");
        }

        List<Event> events = eventRepository.findAllByCategoryId(catId);
        if (!events.isEmpty()) {
            throw new CategoryIsNotEmptyException("The category is not empty");
        }

        repository.deleteById(catId);
    }

    @Override
    public CategoryDto updateCategory(Long catId, CategoryDto categoryDto) {
        if (!repository.existsById(catId)) {
            throw new CategoryNotFoundException("Category with id=" + catId + "was not found");
        }

        List<Category> allByName = repository.findAllByName(categoryDto.getName());
        if (!allByName.isEmpty()) {
            throw new CategoryWithNameExistException("Category with name="
                    + categoryDto.getName() + " already exists");
        }

        Category category = CategoryMapper.categoryDtoToEntity(categoryDto);
        repository.save(category);
        return CategoryMapper.entityToCategoryDto(category);
    }
}
