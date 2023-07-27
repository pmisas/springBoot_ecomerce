package com.test.project.dto.categories;

import com.test.project.dto.item.ItemsDTO;
import com.test.project.entity.Category;
import com.test.project.entity.Item;
import com.test.project.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SendCategoryDTO {


    private Category home;
    private Category electronic;
    private Category beauty;
    private Category health;
    private Category kids;
    private Category transport;
    private Category entertainment;



}
