package com.test.project.security.service;


import com.sun.tools.javac.Main;
import com.test.project.entity.MainUser;
import com.test.project.http_errors.NotFoundException;
import com.test.project.repository.IUserRepository;
import com.test.project.entity.User;
import com.test.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getByEmail(email).get();
        return (UserDetails) MainUser.build(user);
    }


}
