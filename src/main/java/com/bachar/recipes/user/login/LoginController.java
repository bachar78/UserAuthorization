package com.bachar.recipes.user.login;

import com.bachar.recipes.configuration.UserPrincipal;
import com.bachar.recipes.configuration.jwt.JwtIssuer;
import com.bachar.recipes.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

//    @GetMapping("/api/1.0/hello")
//    public String sayHello(@AuthenticationPrincipal UserPrincipal principal) {
//        return "If you get till here, so you are authenticated, welcome " + principal.getUsername();
//    }
}
