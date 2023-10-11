package com.unicauca.backend_registro_calificado.security.auth.Filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        // setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login",
        // "POST"));
    }
}
