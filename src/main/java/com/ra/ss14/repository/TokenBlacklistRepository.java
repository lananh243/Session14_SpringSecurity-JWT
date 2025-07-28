package com.ra.ss14.repository;

import com.ra.ss14.model.entity.BlackListToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenBlacklistRepository extends JpaRepository<BlackListToken, Long> {
    boolean existsByToken(String token);
}
