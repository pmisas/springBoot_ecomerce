package com.test.project.service;

import com.test.project.dto.categories.SendCategoryDTO;
import com.test.project.entity.Category;
import com.test.project.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    ICategoryRepository categoryRepository;

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public SendCategoryDTO getCategory() {
        SendCategoryDTO sendCategoryDTO = new SendCategoryDTO();
        sendCategoryDTO.setHome(getCategoryById(1L));
        sendCategoryDTO.setElectronic(getCategoryById(2L));
        sendCategoryDTO.setBeauty(getCategoryById(3L));
        sendCategoryDTO.setHealth(getCategoryById(4L));
        sendCategoryDTO.setKids(getCategoryById(5L));
        sendCategoryDTO.setTransport(getCategoryById(6L));
        sendCategoryDTO.setEntertainment(getCategoryById(7L));
        return sendCategoryDTO;
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
