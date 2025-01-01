package com.crio.stayease.controller;

import com.crio.stayease.entity.User;
import com.crio.stayease.exchange.AuthRequest;
import com.crio.stayease.exchange.AuthResponse;
import com.crio.stayease.repository.UserRepository;
import com.crio.stayease.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserRepository userRepository;

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/token")
    public ResponseEntity<AuthResponse> getToken(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        User user = userRepository.findByEmail(authRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with given email"));
        String token = jwtUtil.generateToken(authRequest.getUsername(), user.getRole().name());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}

