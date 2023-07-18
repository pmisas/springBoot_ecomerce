package com.test.project.security.controller;

import com.test.project.http_errors.BadRequestException;
import com.test.project.model.ApiResponse;
import com.test.project.security.dto.UserCreateDTO;
import com.test.project.security.entity.User;
import com.test.project.security.service.RolService;
import com.test.project.security.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RolService rolService;

    public ApiResponse<?> newUser(@Valid @ResponseBody UserCreateDTO userCreateDTO,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException("invalid email");
        if(userService.existByEmail(userCreateDTO.getEmail()))
            throw new BadRequestException("email already exist");
        User user =
                new User(userCreateDTO.getEmail(), userCreateDTO.getName(), userCreateDTO.getAddress() ,passwordEncoder.encode(userCreateDTO.getPassword()));
    }
}
