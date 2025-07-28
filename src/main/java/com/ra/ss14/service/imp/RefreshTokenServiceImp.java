package com.ra.ss14.service.imp;

import com.ra.ss14.model.entity.RefreshToken;
import com.ra.ss14.model.entity.User;
import com.ra.ss14.repository.RefreshTokenRepository;
import com.ra.ss14.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImp implements RefreshTokenService {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken createRefreshToken(User user, String ip) {
        RefreshToken token = RefreshToken.builder()
                .user(user)
                .addressIp(ip)
                .token(UUID.randomUUID().toString())
                .expiryDate(LocalDateTime.now().plusDays(7))
                .build();
        return refreshTokenRepository.save(token);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public void deleteByUser(User user) {
        refreshTokenRepository.deleteByUser(user);
    }
}
