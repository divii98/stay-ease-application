package com.crio.stayease.service;

import com.crio.stayease.entity.User;
import com.crio.stayease.exception.ExistWithSameEmailException;
import com.crio.stayease.exchange.UserRegisterRequest;
import com.crio.stayease.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public String registerUser(UserRegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            log.info("User already exist with given email");
            throw new ExistWithSameEmailException("User already exist with same email");
        }
        User user = modelMapper.map(request,User.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return "User registered successfully!!";

    }
}
