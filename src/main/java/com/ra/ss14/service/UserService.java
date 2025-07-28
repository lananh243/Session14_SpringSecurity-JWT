package com.ra.ss14.service;

import com.ra.ss14.model.dto.request.UserLogin;
import com.ra.ss14.model.dto.request.UserRegister;
import com.ra.ss14.model.dto.response.JWTResponse;
import com.ra.ss14.model.entity.User;

public interface UserService {
    User register(UserRegister userRegister);
    JWTResponse login(UserLogin userLogin);
    User findByUsername(String username);
    boolean loginAndSendOTP(UserLogin userLogin);
}
