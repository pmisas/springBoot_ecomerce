package com.test.project.security.controller;

import com.test.project.security.entity.User;
import com.test.project.model.ApiResponse;
import com.test.project.security.service.UserService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @PermitAll
    public ApiResponse addUser(@RequestBody User user) {

        User data = userService.saveUser(user);
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    @GetMapping
    public ApiResponse getUsers() {

        List<User> data = this.userService.getUsers();
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    @GetMapping("/{id}")
    public ApiResponse getUserById(@PathVariable Long id) {

        User data = userService.getUserById(id);
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    @PutMapping
    public ApiResponse updateUserAddress(@RequestBody User user) {

        User data = userService.updateUser(user);
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteUserById(@PathVariable Long id) {

        String data = userService.deleteUserById(id);
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }

}
