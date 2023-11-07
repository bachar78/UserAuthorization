package com.bachar.recipes.configuration.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.bachar.recipes.configuration.UserPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtToPrincipalConvertor {
    public UserPrincipal convert(DecodedJWT jwt) {
        return UserPrincipal.builder()
                .userId(Long.valueOf(jwt.getSubject()))
                .username(jwt.getClaim("username").asString())
                .authorities(getAuthorities(jwt))
                .build();
    }

    private List<SimpleGrantedAuthority> getAuthorities(DecodedJWT jwt) {

        var claim = jwt.getClaim("authorities");
        // Guard clauses
        if (claim.isNull() || claim.isMissing()) return List.of();
        return claim.asList(SimpleGrantedAuthority.class);
    }
}
