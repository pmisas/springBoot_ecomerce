package com.test.project.service;

import com.test.project.entity.User;
import com.test.project.http_errors.BadRequestException;
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

    public User saveUser(User user) {
        User copyUser = userRepository.findByEmail(user.getEmail()).orElse(null);
        if(copyUser != null){
            throw new BadRequestException("Email: " + user.getEmail() + " already exist in db");
        }else{
            return userRepository.save(user);
        }

    }


    public List<User> getUsers() {

        return userRepository.findAll();
    }

    public User getUserById(Long id) {

        return userRepository.findById(id).orElseThrow(()-> new NotFoundException("User not found with ID: " + id));
    }

    public String deleteUserById(Long id) {
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                userRepository.deleteById(id);
                return "User " + id + " removed!";
            } else {
                throw new NotFoundException("User not found with ID: " + id);
            }
    }

    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).
                orElseThrow(()-> new NotFoundException("User not found with ID: " + user.getId()));
        existingUser.setAddress(user.getAddress());
        return userRepository.save(existingUser);
    }

}
