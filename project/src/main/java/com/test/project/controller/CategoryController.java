package com.test.project.controller;

import com.test.project.entity.Category;
import com.test.project.entity.User;
import com.test.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Category addCategory(@RequestBody Category category) {

        return categoryService.saveCategory(category);
    }

    @GetMapping
    public List<Category> getCategory() {
        return this.categoryService.getCategory();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping
    public Category updateCategoryAddress(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("/{id}")
    public String deleteCategoryById(@PathVariable Long id) {
        return categoryService.deleteCategoryById(id);
    }

}