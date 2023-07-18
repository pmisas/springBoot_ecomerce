package com.test.project.security.controller;

import com.test.project.http_errors.BadRequestException;
import com.test.project.model.ApiResponse;
import com.test.project.security.dto.JwtDTO;
import com.test.project.security.dto.LoginUserDTO;
import com.test.project.security.dto.UserCreateDTO;
import com.test.project.security.entity.Rol;
import com.test.project.security.entity.User;
import com.test.project.security.enums.RolName;
import com.test.project.security.jwt.JwtProvider;
import com.test.project.security.service.RolService;
import com.test.project.security.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserService userService;

    @Autowired
    RolService rolService;

    @PostMapping("/register")
    public ApiResponse<?> newUser(@Valid @RequestBody UserCreateDTO userCreateDTO,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException("invalid form");
        if(userService.existByEmail(userCreateDTO.getEmail()))
            throw new BadRequestException("email already exist");
        User user =
                new User(userCreateDTO.getEmail(), userCreateDTO.getName(), userCreateDTO.getAddress(),
                        passwordEncoder.encode(userCreateDTO.getPassword()));
        Set<Rol> rols = new HashSet<>();
        rols.add(rolService.getByRolName(RolName.ROLE_BUYER).get());
        if(userCreateDTO.getRoles().contains("seller"))
            rols.add(rolService.getByRolName(RolName.ROLE_SELLER).get());
        user.setRoles(rols);
        userService.save(user);
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(user);
        return response;
    }

    public ApiResponse<JwtDTO> login(@Valid @RequestBody LoginUserDTO loginUserDTO,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException("invalid form");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginUserDTO.getEmail(), loginUserDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(jwtDTO);
        return response;
    }
}
