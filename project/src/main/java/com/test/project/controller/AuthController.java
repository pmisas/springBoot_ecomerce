package com.test.project.controller;

import com.test.project.dto.LoginUserDTO;
import com.test.project.dto.UserCreateDTO;
import com.test.project.entity.User;
import com.test.project.enums.RolName;
import com.test.project.model.ApiResponse;
//import com.test.project.security.jwt.JwtProvider;
import com.test.project.security.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

        private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginUserDTO login) {

        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(userService.login(login));
        return response;
    }


    @PostMapping("/register")
    public ApiResponse register (@RequestBody UserCreateDTO register){

        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(userService.Register(register));
        return response;
    }

/*
    @PostMapping("/register")
    public User register(@RequestBody User user, @RequestParam Set<RolName> roles) {
        return userService.registerUser(user, roles);
    }

 */

    /*
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserService userService;

    @Autowired
    RolService rolService;

    @PostMapping("/register")
    public ApiResponse<?> newUser(@Validated @RequestBody UserCreateDTO userCreateDTO,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException("invalid form");
        if(userService.existsByEmail(userCreateDTO.getEmail()))
            throw new BadRequestException("email already exist");
        User user =
                new User(userCreateDTO.getEmail(), userCreateDTO.getName(), userCreateDTO.getAddress(),
                        passwordEncoder.encode(userCreateDTO.getPassword()));
        Set<Rol> roles = new HashSet<>();
        //roles.add(rolService.getByRolName(RolName.ROLE_BUYER));
        if(userCreateDTO.getRoles().contains("seller"))
        //    roles.add(rolService.getByRolName(RolName.ROLE_SELLER));
        user.setRoles(roles);
        userService.save(user);
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(user);
        return response;
    }

    @PostMapping("/login")
    public ApiResponse<JwtDTO> login(@Validated @RequestBody LoginUserDTO loginUserDTO,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException("invalid form");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUserDTO.getEmail(), loginUserDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(jwtDTO);
        return response;
    }
    */

}

