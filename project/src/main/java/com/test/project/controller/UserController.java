package com.test.project.controller;

import com.google.protobuf.Api;
import com.test.project.entity.User;
import com.test.project.http_errors.NotFoundException;
import com.test.project.model.ApiResponse;
import com.test.project.security.filter.JwtFilter;
import com.test.project.security.service.JwtService;
import com.test.project.security.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @GetMapping
    public ApiResponse getUserInfo(@RequestHeader("Authorization") String tokenHeader) {

        //String token = tokenHeader.replace("Bearer ", ""); // Remover la palabra "Bearer " del token

        //String data = jwtService.getEmailFromToken(token);

        //User user = userService.getByEmail(data).orElseThrow(()-> new NotFoundException("No se encontró el user"));
        logger.info("TOKEN : "+ tokenHeader);

        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData("holi");
        return response;
    }

}
