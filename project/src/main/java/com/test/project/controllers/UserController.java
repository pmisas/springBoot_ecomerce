package com.test.project.controllers;

import com.test.project.model.User;
import com.test.project.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ArrayList<User> getUsers() {
        return this.userService.getUsers();
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return this.userService.saveUser(user);
    }

    @GetMapping(path = "/{id}")
    public Optional<User> getUserById(@PathVariable ("id") Long id) {
        return this.userService.getById(id);
    }

    @PatchMapping(path = "/{id}")
    public User updateUserById(@PathVariable ("id")Long id, @RequestBody User request) {
        return this.userService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteUserById(@PathVariable ("id")Long id) {
        boolean ok = this.userService.deleteUserById(id);

        if(ok) {
            return "User with id " + id + " deleted";
        } else {
            return "Error, can't delete user with id " + id;
        }
    }
}
