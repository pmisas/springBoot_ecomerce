package com.test.project.dto;

import com.test.project.dto.item.ItemDTO;
import com.test.project.dto.item.ItemsDTO;
import com.test.project.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SellerDTO {
    private Long id;
    private String name;
    private String email;
    private String address;
    private List<ItemDTO> items;

    public static SellerDTO fromUser(User seller) {
        SellerDTO sellerDTO = new SellerDTO();
        sellerDTO.setId(seller.getId());
        sellerDTO.setName(seller.getName());
        sellerDTO.setEmail(seller.getEmail());
        sellerDTO.setAddress(seller.getAddress());
/*
        // Si deseas incluir solo información relevante de los ítems del vendedor, puedes usar un ItemDTO
        List<ItemsDTO> itemDTOs = seller.getItems().stream()
                .map(ItemsDTO::fromItem)
                .collect(Collectors.toList());
        sellerDTO.setItems(itemDTOs);
*/
        return sellerDTO;
    }
}
