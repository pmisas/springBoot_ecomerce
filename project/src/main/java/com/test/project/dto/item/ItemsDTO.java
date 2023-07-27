package com.test.project.dto.item;

import com.test.project.dto.SellerDTO;
import com.test.project.entity.Category;
import com.test.project.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemsDTO {
    private Long id;
    private String name;
    private String image;
    private String price;
    private Integer stock;
    private String description;
    private SellerDTO seller;
    private List<Category> categories;


    public static ItemsDTO fromItem(Item item) {
        ItemsDTO itemsDTO = new ItemsDTO();
        itemsDTO.setId(item.getId());
        itemsDTO.setName(item.getName());
        itemsDTO.setImage(item.getImage());
        itemsDTO.setPrice(item.getPrice());
        itemsDTO.setStock(item.getStock());
        itemsDTO.setDescription(item.getDescription());

        itemsDTO.setSeller(SellerDTO.fromUser(item.getSeller()));

        itemsDTO.setCategories(item.getCategories());

        return itemsDTO;
    }
    }
