package com.ra.ss14.service;

import com.ra.ss14.model.entity.RefreshToken;
import com.ra.ss14.model.entity.User;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(User user, String ip);
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(User user);
}
