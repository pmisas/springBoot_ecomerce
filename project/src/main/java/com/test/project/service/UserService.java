package com.test.project.service;

import com.test.project.entity.User;
import com.test.project.http_errors.NotFoundException;
import com.test.project.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public void save(User user){
        userRepository.save(user);
    }


    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
    }


    public String deleteUserById(Long id) {
        userRepository.deleteById(id);
        return "user " + id + " removed!";
    }

    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(() -> new NotFoundException("User not found with ID: " + user.getId()));
        existingUser.setAddress(user.getAddress());
        return userRepository.save(existingUser);
    }



}
