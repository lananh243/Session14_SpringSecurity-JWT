package com.ra.ss14.controller;

import com.ra.ss14.model.dto.response.APIResponse;
import com.ra.ss14.model.entity.User;
import com.ra.ss14.security.jwt.JWTProvider;
import com.ra.ss14.service.RefreshTokenService;
import com.ra.ss14.service.TokenBlackListService;
import com.ra.ss14.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

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

    @PostMapping("/refresh-token")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestBody Map<String, String> body) {
        String refreshToken = body.get("refresh_token");

        return refreshTokenService.findByToken(refreshToken)
                .filter(token -> token.getExpiryDate().isAfter(LocalDateTime.now()))
                .map(token -> {
                    String newAccessToken = jwtProvider.generateToken(token.getUser().getUsername());
                    return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
                })
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("message", "Invalid refresh token")));
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body(Map.of("message", "Missing token"));
        }

        String token = authHeader.substring(7); // Cắt "Bearer "
        String username = jwtProvider.getUsernameFromToken(token);

        // Xóa refresh token khỏi DB
        User user = userService.findByUsername(username);
        refreshTokenService.deleteByUser(user);

        // Đưa access token vào blacklist
        tokenBlackListService.blacklist(token);

        return ResponseEntity.ok(Map.of("message", "Đăng xuất thành công"));
    }

}
