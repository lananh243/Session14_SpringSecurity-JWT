package com.ra.ss14.service.imp;

import com.ra.ss14.model.dto.request.UserLogin;
import com.ra.ss14.model.dto.request.UserRegister;
import com.ra.ss14.model.dto.response.JWTResponse;
import com.ra.ss14.model.entity.User;
import com.ra.ss14.repository.UserRepository;
import com.ra.ss14.security.jwt.JWTProvider;
import com.ra.ss14.security.principal.CustomerUserDetails;
import com.ra.ss14.service.EmailService;
import com.ra.ss14.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private EmailService emailService;

    @Override
    public User register(UserRegister userRegister) {
        if (userRepository.existsByUsername(userRegister.getUsername())) {
            throw new RuntimeException("User đã tồn tại");
        }

        if (userRepository.existsByEmail(userRegister.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }

        User user = User.builder()
                .username(userRegister.getUsername())
                .password(passwordEncoder.encode(userRegister.getPassword()))
                .email(userRegister.getEmail())
                .role(userRegister.getRole())
                .build();
        return userRepository.save(user);
    }

    @Override
    public JWTResponse login(UserLogin userLogin) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword())
            );

            String token = jwtProvider.generateToken(userLogin.getUsername());
            String refreshToken = jwtProvider.generateToken(userLogin.getUsername());

            return new JWTResponse(token, refreshToken);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Thông tin đăng nhập không hợp lệ!");
        }
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("Username not found!"));
    }

    @Override
    public boolean loginAndSendOTP(UserLogin userLogin) {
        // Tìm người dùng theo tên đăng nhập
        User user = findByUsername(userLogin.getUsername());
        if (user == null) {
            return false;
        } else {
            if (passwordEncoder.matches(userLogin.getPassword(), user.getPassword())) {
                String otp = generateOtp();
                user.setOtp(otp);
                userRepository.save(user);

                emailService.sendEmail(user.getEmail(), "OTP verified", otp);
                return true;
            }
            return false;
        }
    }
    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Sinh OTP 6 chữ số
        return String.valueOf(otp);
    }



}
