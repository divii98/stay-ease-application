package com.crio.stayease.service;

import com.crio.stayease.exchange.UserRegisterRequest;

public interface UserService {
    String registerUser(UserRegisterRequest request);
}
