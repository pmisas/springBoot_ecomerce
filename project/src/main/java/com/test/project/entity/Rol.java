package com.test.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.test.project.enums.RolName;
import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    RolName name;

}
