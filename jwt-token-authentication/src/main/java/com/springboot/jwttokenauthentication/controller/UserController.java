package com.springboot.jwttokenauthentication.controller;

import com.springboot.jwttokenauthentication.dto.AuthRequest;
import com.springboot.jwttokenauthentication.dto.CreateUserRequest;
import com.springboot.jwttokenauthentication.model.User;
import com.springboot.jwttokenauthentication.service.JwtService;
import com.springboot.jwttokenauthentication.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class UserController {
    private final UserService service;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService service, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome page";
    }

    @PostMapping("/addNewUser")
    public User addUser(@RequestBody CreateUserRequest request){
        return service.createUser(request);
    }

    @PostMapping("/generateToken")
    public String generateToken(@RequestBody AuthRequest request){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(request.username());
        }
        log.info("user not found!" + request.username());
        throw new UsernameNotFoundException("user not found!" + request.username());
    }

    @GetMapping("/user")
    public String getUserString(){
        return "user page";
    }

    @GetMapping("/admin")
    public String getAdminString(){
        return "admin page";
    }
}
