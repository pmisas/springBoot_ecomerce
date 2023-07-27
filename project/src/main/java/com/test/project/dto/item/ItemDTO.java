package com.test.project.dto.item;

import lombok.Data;

import java.util.List;

@Data
public class ItemDTO {
    private Long id;
    private String name;
    private String image;
    private String price;
    private Integer stock;
    private String description;

    private List<Long> categories_ids;
}
