package com.test.project.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private String name;  //nombre del comprador
    private String address;  //direccion del comprador
    private Integer number;  //numero de productos comprados
    private Integer price;   //valor de la compra
    private String dateOrder;   //fecha de la compra

    //private Long seller_id;  // vendedor
    private List<Long> items_id;  //items
}
