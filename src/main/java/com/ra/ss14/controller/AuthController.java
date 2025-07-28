package com.ra.ss14.controller;

import com.ra.ss14.security.jwt.JWTProvider;
import com.ra.ss14.service.RefreshTokenService;
import com.ra.ss14.service.TokenBlackListService;
import com.ra.ss14.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private TokenBlackListService tokenBlackListService;
    @Autowired
    private UserService userService;


}
