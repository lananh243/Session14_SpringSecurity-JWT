package com.ra.ss14.controller;

import com.ra.ss14.model.dto.request.UserLogin;
import com.ra.ss14.model.dto.request.UserRegister;
import com.ra.ss14.model.dto.response.APIResponse;
import com.ra.ss14.model.entity.User;
import com.ra.ss14.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<APIResponse<?>> registerUser(@RequestBody UserRegister userRegister) {
        return new ResponseEntity<>(new APIResponse<>(true, "Đăng kí thành công!", userService.register(userRegister), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<?>> login(@RequestBody UserLogin userLogin) {
        return new ResponseEntity<>(new APIResponse<>(true, "Đăng nhập thành công!", userService.login(userLogin), HttpStatus.OK), HttpStatus.OK);
    }
}
