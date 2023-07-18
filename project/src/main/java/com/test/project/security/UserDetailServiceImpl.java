package com.test.project.security;

import com.test.project.entity.User;
import com.test.project.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    IUserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         User user = userRepository
                .findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with email: " + email));
         return new UserDetailsImpl(user);
    }
}
