package com.test.project.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginUserDTO {
    @NotBlank
    @Email
    String email;
    @NotBlank
    @Size(min = 3, max=8)
    String password;
}
