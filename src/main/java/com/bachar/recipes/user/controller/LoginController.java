package com.bachar.recipes.user.controller;

import com.bachar.recipes.configuration.UserPrincipal;
import com.bachar.recipes.configuration.jwt.JwtIssuer;
import com.bachar.recipes.user.User;
import com.bachar.recipes.user.login.LoginResponse;
import com.bachar.recipes.user.login.LoginShem;
import com.bachar.recipes.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    @PostMapping("/api/1.0/login")
    public LoginResponse handleLogin(@RequestBody @Validated LoginShem user) {
       return authService.attemptLogin(user);
    }

    @GetMapping("/api/1.0/hello")
    public String handleLogin(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return "If you can see this message that means that you "+ userPrincipal.getUsername() + " are authenticated";
    }

}
