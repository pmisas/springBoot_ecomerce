package com.test.project.dto;

import com.test.project.entity.Rol;
import com.test.project.enums.RolName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    RolName roles;
}
