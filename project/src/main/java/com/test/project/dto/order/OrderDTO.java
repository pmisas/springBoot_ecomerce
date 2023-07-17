package com.test.project.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    @NotBlank
    @Size(min = 5, max=30)
    private String name;  //nombre del comprador
    @NotBlank
    private String address;  //direccion del comprador
    @NotNull
    private Integer number;  //numero de productos comprados
    @NotBlank
    private Integer price;   //valor de la compra
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOrder;   //fecha de la compra
    @NotNull
    //private Long seller_id;  // vendedor
    private List<Long> items_id;  //items
}
