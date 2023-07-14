package com.test.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private String image;
    private Integer price;
    private Integer stock;
    private String description;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories;

    @ManyToMany(mappedBy = "items")
    private List<Cart> carts ;
}
