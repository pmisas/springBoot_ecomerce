package com.test.project.controller;

import com.test.project.dto.security.JwtDTO;
import com.test.project.dto.security.LoginUserDTO;
import com.test.project.dto.security.UserCreateDTO;
import com.test.project.entity.Rol;
import com.test.project.entity.User;
import com.test.project.enums.RolName;
import com.test.project.http_errors.BadRequestException;
import com.test.project.model.ApiResponse;
import com.test.project.security.jwt.JwtProvider;
import com.test.project.service.RolService;
import com.test.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

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
    public ApiResponse<?> newUser(@Validated @RequestBody UserCreateDTO userCreateDTO,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException("invalid form");
        if(userService.existsByEmail(userCreateDTO.getEmail()))
            throw new BadRequestException("email already exist");
        User user =
                new User(userCreateDTO.getEmail(), userCreateDTO.getName(), userCreateDTO.getAddress(),
                        passwordEncoder.encode(userCreateDTO.getPassword()));
        Set<Rol> roles = new HashSet<>();
        //roles.add(rolService.getByRolName(RolName.ROLE_BUYER));
        if(userCreateDTO.getRoles().contains("seller"))
        //    roles.add(rolService.getByRolName(RolName.ROLE_SELLER));
        user.setRoles(roles);
        userService.save(user);
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(user);
        return response;
    }

    @PostMapping("/login")
    public ApiResponse<JwtDTO> login(@Validated @RequestBody LoginUserDTO loginUserDTO,
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

    @GetMapping("/public")
    public ApiResponse<?> getMensaje() {
        logger.info("Obteniendo el mensaje");

        var auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Datos del Usuario: {}", auth.getPrincipal());
        logger.info("Datos de los Roles {}", auth.getAuthorities());
        logger.info("Esta autenticado {}", auth.isAuthenticated());

        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData("publico");
        return response;
    }
}

