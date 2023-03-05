package ru.practicum.mainService.controller.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.mainService.dto.category.CategoryDto;
import ru.practicum.mainService.dto.category.NewCategoryDto;
import ru.practicum.mainService.service.api.admins.CategoryServiceAdmin;

@RestController
@RequestMapping(path = "/admin/categories")
public class CategoryControllerAdmin {

    private final CategoryServiceAdmin categoryService;

    @Autowired
    public CategoryControllerAdmin(CategoryServiceAdmin categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody NewCategoryDto newCategoryDto) {
        CategoryDto categoryDto = categoryService.addCategory(newCategoryDto);
        ResponseEntity<CategoryDto> response = new ResponseEntity<>(categoryDto, HttpStatus.OK);
        return response;
    }


}
