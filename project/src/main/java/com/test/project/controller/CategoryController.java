package com.test.project.controller;

import com.test.project.dto.categories.SendCategoryDTO;
import com.test.project.entity.Category;
import com.test.project.entity.User;
import com.test.project.model.ApiResponse;
import com.test.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/categories")
    public Category addCategory(@RequestBody Category category) {

        return categoryService.saveCategory(category);
    }

    @GetMapping("/public/categories")
    public ApiResponse getCategory() {
        SendCategoryDTO data = this.categoryService.getCategory();
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    @GetMapping("/public/categories/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/categories")
    public Category updateCategoryAddress(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategoryById(@PathVariable Long id) {
        return categoryService.deleteCategoryById(id);
    }

}