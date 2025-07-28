package com.ra.ss14.service;

public interface TokenBlackListService {
    boolean isBlackListed(String token);
    void blacklist(String token);
}
