package ru.practicum.mainService.controller.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainService.dto.category.CategoryDto;
import ru.practicum.mainService.dto.category.NewCategoryDto;
import ru.practicum.mainService.service.api.admins.CategoryServiceAdmin;
import ru.practicum.mainService.validator.CategoryValidator;

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
    public ResponseEntity<CategoryDto> addCategory(@RequestBody NewCategoryDto newCategoryDto) {
        validator.validateCategory(newCategoryDto);
        CategoryDto categoryDto = categoryService.addCategory(newCategoryDto);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{catId}")
    public ResponseEntity deleteCategory(@PathVariable(name = "catId") Long catId) {
        categoryService.deleteCategory(catId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(path = "/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable(name = "catId") Long catId,
                                                      @RequestBody CategoryDto categoryDto) {
        validator.validateCategory(categoryDto);
        CategoryDto updatedCategory = categoryService.updateCategory(catId, categoryDto);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

}
