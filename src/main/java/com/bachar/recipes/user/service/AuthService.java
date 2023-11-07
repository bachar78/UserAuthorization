package com.bachar.recipes.user.service;

import com.bachar.recipes.configuration.UserPrincipal;
import com.bachar.recipes.configuration.jwt.JwtIssuer;
import com.bachar.recipes.user.User;
import com.bachar.recipes.user.login.LoginResponse;
import com.bachar.recipes.user.login.LoginShem;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;

    public LoginResponse attemptLogin(LoginShem user) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (UserPrincipal) authentication.getPrincipal();
        var roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();
        var token = jwtIssuer.issue(principal.getUserId(), principal.getUsername(), roles);
        return LoginResponse.builder()
                .accessToken(token)
                .build();
    }

}
