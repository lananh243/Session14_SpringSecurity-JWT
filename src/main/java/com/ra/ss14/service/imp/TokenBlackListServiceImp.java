package com.ra.ss14.service.imp;

import com.ra.ss14.model.entity.BlackListToken;
import com.ra.ss14.repository.TokenBlacklistRepository;
import com.ra.ss14.service.TokenBlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenBlackListServiceImp implements TokenBlackListService {
    @Autowired
    private TokenBlacklistRepository tokenBlacklistRepository;

    @Override
    public boolean isBlackListed(String token) {
        return tokenBlacklistRepository.existsByToken(token);
    }

    @Override
    public void blacklist(String token) {
        if (!isBlackListed(token)) {
            BlackListToken blackListToken = new BlackListToken();
            tokenBlacklistRepository.save(blackListToken);
        }
    }
}
