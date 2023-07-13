package com.test.project.services;

import com.test.project.model.User;
import com.test.project.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired(required = false)
    private IUserRepository userRepository;


    @Override
    public ArrayList<User> getUsers() {
        return (ArrayList<User>) userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateById(User request, Long id) {
        User user = userRepository.findById(id).get();

        user.setName(request.getName());

        return user;
    }

    @Override
    public Boolean deleteUserById(Long id) {
        try{
            userRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }



}
