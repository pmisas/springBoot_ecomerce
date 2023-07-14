package com.test.project.entity;

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
    private Integer price;
    private Integer stock;
    private String description;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    /*@JsonIgnoreProperties(value = { "items" ,"otherPropertyToIgnore" })*/
    @JsonIgnore
    @JoinColumn(name = "seller_id")
    private User seller;

    @ManyToMany(mappedBy = "items")
    private List<Order> orders = new ArrayList<>();

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    @ManyToMany(mappedBy = "items")
    private List<Cart> carts = new ArrayList<>();
}
