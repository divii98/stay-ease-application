package com.crio.stayease.controller;

import com.crio.stayease.exchange.AuthRequest;
import com.crio.stayease.exchange.UserRegisterRequest;
import com.crio.stayease.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Log4j2
public class UserController {
    UserService userService;
    AuthenticationManager authenticationManager;

    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody UserRegisterRequest request) {
        log.info("Register request called for : " + request.getEmail());
        return userService.registerUser(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/login")
    public String loginUser(@Valid @RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        return "User logged in successfully";
    }
}
