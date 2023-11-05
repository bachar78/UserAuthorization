package com.bachar.recipes.configuration;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtToPrincipalConvertor {
    private UserPrincipal convert(DecodedJWT jwt) {
        return UserPrincipal.builder()
                .userId(Long.valueOf(jwt.getSubject()))
                .username(jwt.getClaim("username").asString())
                .authorities(getAuthorities(jwt))
                .build();
    }

    private List<SimpleGrantedAuthority> getAuthorities(DecodedJWT jwt) {

        var claim = jwt.getClaim("authorities");
        // Guard clauses
        if (claim.isMissing() || claim.isMissing()) return List.of();
        return claim.asList(SimpleGrantedAuthority.class);
    }
}
