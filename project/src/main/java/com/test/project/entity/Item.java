package com.test.project.entity;

import com.test.project.entity.Category;
import com.test.project.security.entity.User;
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
    private String image;
    private Integer price;
    private Integer stock;
    private String description;
    private User seller;

/*
    @ManyToOne
    @JsonIgnoreProperties("items")
    @JoinColumn(name = "seller_id")
    private User user;


 */
    /*
    @ManyToMany(mappedBy = "items")
    private List<Order> orders = new ArrayList<>();

     */

    @ManyToMany
    @JoinTable(
            name = "category_item",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();


}
