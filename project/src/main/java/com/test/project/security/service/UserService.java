package com.test.project.security.service;

import com.test.project.entity.Rol;
import com.test.project.entity.User;
import com.test.project.dto.LoginUserDTO;
import com.test.project.dto.UserCreateDTO;
import com.test.project.http_errors.BadRequestException;
import com.test.project.http_errors.NotFoundException;
import com.test.project.repository.IRolRepository;
import com.test.project.repository.IUserRepository;
import com.test.project.model.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRolRepository rolRepository;

    @Autowired
    private JwtService jwtService;

    public String login(LoginUserDTO login) {

        /*
        User user = userRepository.findByEmail(login.getEmail())
                .orElseThrow(()-> new NotFoundException("user with email"+ login.getEmail() +"not found"));
        if(user.getPassword() != login.getPassword()) {
            new BadRequestException("wrong password");
        }
        return "login from public endpoint";

         */
        return "holi";
    }

    public AuthResponse Register(UserCreateDTO register) {
        if(userRepository.existsByEmail(register.getEmail())) {
            new BadRequestException("email in use, try with another email");
        }
        Set<Rol> userRoles =  rolRepository.findByName(register.getRoles());
        User user = User.builder()
                .email(register.getEmail())
                .password(register.getPassword())
                .name(register.getName())
                .address(register.getAddress())
                .roles(userRoles)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    /*
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
     */

}
