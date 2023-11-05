package com.bachar.recipes.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bachar.recipes.user.login.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtIssuer {

    private final JwtProperties jwtProperties;
    public String issue(Long userId, String username, List<String> authorities) {
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.DAYS))
                .withClaim("username", username)
                .withClaim("authorities", authorities)
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
    }
}
