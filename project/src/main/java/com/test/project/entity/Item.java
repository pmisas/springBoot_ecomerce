package com.test.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private String price;
    private Integer stock;
    private String description;


    @ManyToOne
    @JsonBackReference
    @JsonIgnoreProperties("items")
    @JoinColumn(name = "seller_id")
    private User seller;

    /*
    @ManyToMany(mappedBy = "items")
    private List<Order> orders = new ArrayList<>();

     */

    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "category_item",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();


}
