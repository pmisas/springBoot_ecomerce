package com.test.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; //nombre del comprador
    private String address; //direccion del comprador
    private Integer number;  //numero de productos comprados
    private Integer price;  //valor de la compra
    private LocalDateTime dateOrder;   //fecha de la compra

    // vendedor
    @Column(name = "user_id")
    private Long userId;
    /*
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    */

    //items
    @ManyToMany
    @JoinTable(
            name = "order_item",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items;

}
