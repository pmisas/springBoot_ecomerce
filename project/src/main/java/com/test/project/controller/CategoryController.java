package com.test.project.controller;

import com.test.project.entity.Category;
import com.test.project.model.ApiResponse;
import com.test.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ApiResponse addCategory(@RequestBody Category category) {

        Category data = categoryService.saveCategory(category);
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    @GetMapping
    public ApiResponse getCategory() {

        List<Category> data = this.categoryService.getCategory();
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    @GetMapping("/{id}")
    public ApiResponse getCategoryById(@PathVariable Long id) {
        Category data = categoryService.getCategoryById(id);
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    @PutMapping
    public ApiResponse updateCategoryAddress(@RequestBody Category category) {
        Category data = categoryService.updateCategory(category);
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteCategoryById(@PathVariable Long id) {
        String data =  categoryService.deleteCategoryById(id);
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }

}
