package com.test.project.services;

import com.test.project.model.User;

import java.util.ArrayList;
import java.util.Optional;

public interface IUserService {
    public ArrayList<User> getUsers();
    public User saveUser(User user);
    public Optional<User> getById(Long id);
    public User updateById(User request, Long id);
    public Boolean deleteUserById(Long id);
}
