package com.bachar.recipes.user.login;

import com.bachar.recipes.configuration.JwtIssuer;
import com.bachar.recipes.error.ApiError;
import com.bachar.recipes.user.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final JwtIssuer jwtIssuer;

    @PostMapping("/api/1.0/login")
   public LoginResponse handleLogin(@RequestBody @Validated User user) {
        var token = jwtIssuer.issue(1L, user.getUsername(), List.of("USER"));
        return LoginResponse.builder()
                .accessToken(token)
                .build();
    }


}
