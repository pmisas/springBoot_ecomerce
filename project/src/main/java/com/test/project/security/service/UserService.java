package com.test.project.security.service;

import com.test.project.entity.Rol;
import com.test.project.entity.User;
import com.test.project.dto.LoginUserDTO;
import com.test.project.dto.UserCreateDTO;
import com.test.project.enums.RolName;
import com.test.project.http_errors.BadRequestException;
import com.test.project.http_errors.NotFoundException;
import com.test.project.repository.IRolRepository;
import com.test.project.repository.IUserRepository;
import com.test.project.model.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRolRepository rolRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;


    public AuthResponse login(LoginUserDTO request) {
        UserDetails user=userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new NotFoundException("Email no encontrado"));

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            String token=jwtService.getToken(user);
            return AuthResponse.builder()
                    .token(token)
                    .build();
        } else {
            throw new BadRequestException("Contraseña incorrecta");
        }
    }

    public AuthResponse Register(UserCreateDTO register) {
        if(userRepository.existsByEmail(register.getEmail())) {
            throw new BadRequestException("email in use, try with another email");
        }

        Set<Rol> userRoles = rolRepository.findByNameIn(register.getRoles());
        User user = User.builder()
                .email(register.getEmail())
                .password(passwordEncoder.encode(register.getPassword()))
                .name(register.getName())
                .address(register.getAddress())
                .roles(userRoles)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);
    }

/*

    public User registerUser(User user,  Set<RolName> role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Rol userRole = (Rol) rolRepository.findByName(role);
        user.setRoles(Collections.singleton(userRole));
        return userRepository.save(user);
    }
*/
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
