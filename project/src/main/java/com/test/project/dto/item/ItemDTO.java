package com.test.project.dto.item;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class ItemDTO {
    private Long id;
    @NotBlank
    @Size(min=3, max=20)
    private String name;
    @NotBlank
    private String image;
    @NotBlank
    private Integer price;
    @NotBlank
    private Integer stock;
    @NotBlank
    @Size(min=10, max=100)
    private String description;
    @NotNull
    private List<Long> categories_ids;
}
