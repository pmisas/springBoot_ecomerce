package com.test.project.dto.security;

import com.test.project.entity.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserCreateDTO {
    @NotBlank
    @Size(min = 5, max=30)
    String name;
    @NotBlank
    @Email
    String email;
    @NotBlank
    @Size(min = 3, max=8)
    String password;
    @NotBlank
    @Size(min = 8, max=20)
    String address;
    Set<Rol> roles = new HashSet<>();
}
