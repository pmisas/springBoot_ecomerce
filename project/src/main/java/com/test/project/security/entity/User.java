package com.test.project.security.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.test.project.entity.Item;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    @Size(min = 5, max=30)
    String name;
    @Email
    String email;
    @Size(min = 3, max=8)
    String password;
    @Size(min = 8, max=20)
    String address;
    @NotNull
    @ManyToMany
    @JoinTable(name = "user_rol",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns =  @JoinColumn(name = "rol_id"))
    Set<Rol> roles = new HashSet<>();
/*
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user",cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
*/

    @JsonIgnoreProperties("seller")
    /*@JsonBackReference*/
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();
}
