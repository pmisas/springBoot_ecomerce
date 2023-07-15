package com.test.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    String name;
    String email;
    String password;
    String address;
/*
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user",cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
*/

    @JsonIgnoreProperties("seller")
    /*@JsonBackReference*/
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();
}
