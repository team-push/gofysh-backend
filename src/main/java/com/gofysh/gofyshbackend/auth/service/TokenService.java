package com.gofysh.gofyshbackend.auth.service;

import org.springframework.security.core.Authentication;

public interface TokenService {
    String createToken(Authentication authentication);
    String getUsernameFromJWT(String token);
    boolean validateToken(String authToken);
}
