package com.test.project.service;

import com.test.project.entity.Category;
import com.test.project.entity.Item;
import com.test.project.repository.ICategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    @Autowired
    ICategoryRepository categoryRepository;

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public String deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
        return "category " + id + " removed!";
    }

    public Category updateCategory(Category category) {
        Category existingCategory = categoryRepository.findById(category.getId()).orElse(null);
        existingCategory.setName(category.getName());
        return categoryRepository.save(existingCategory);
    }
}
