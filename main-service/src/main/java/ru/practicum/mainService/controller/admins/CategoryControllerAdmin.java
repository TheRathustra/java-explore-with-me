package ru.practicum.mainService.controller.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainService.dto.category.CategoryDto;
import ru.practicum.mainService.dto.category.NewCategoryDto;
import ru.practicum.mainService.service.api.admins.CategoryServiceAdmin;
import ru.practicum.mainService.validator.CategoryValidator;

import javax.transaction.Transactional;

@RestController
@RequestMapping(path = "/admin/categories")
public class CategoryControllerAdmin {

    private final CategoryServiceAdmin categoryService;

    private final CategoryValidator validator;

    @Autowired
    public CategoryControllerAdmin(CategoryServiceAdmin categoryService, CategoryValidator categoryValidator) {
        this.categoryService = categoryService;
        this.validator = categoryValidator;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoryDto> addCategory(@RequestBody NewCategoryDto newCategoryDto) {
        //имя категории должно быть уникальным
        validator.validateCategory(newCategoryDto);
        CategoryDto categoryDto = categoryService.addCategory(newCategoryDto);
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{catId}")
    @Transactional
    public ResponseEntity deleteCategory(@PathVariable(name = "catId") Long catId) {
        //с категорией не должно быть связано ни одного события.
        categoryService.deleteCategory(catId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(path = "/{catId}")
    @Transactional
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable(name = "catId") Long catId,
                                                      @RequestBody CategoryDto categoryDto) {
        //имя категории должно быть уникальным
        validator.validateCategory(categoryDto);
        CategoryDto updatedCategory = categoryService.updateCategory(catId, categoryDto);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

}
