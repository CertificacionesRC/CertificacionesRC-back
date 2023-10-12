package com.unicauca.backend_registro_calificado.security;

import com.unicauca.backend_registro_calificado.security.auth.Filter.JWTAuthenticationFilter;
import com.unicauca.backend_registro_calificado.security.auth.Filter.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
/*
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        RequestMatcher publicMatchers = new OrRequestMatcher(new AntPathRequestMatcher("/"),
                new AntPathRequestMatcher("/css/**"), new AntPathRequestMatcher("/js/**"),
                new AntPathRequestMatcher("/images/**"));

        http.authorizeHttpRequests(authorize -> {
                    try {
                        authorize.requestMatchers(publicMatchers).permitAll().anyRequest().authenticated();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }).csrf(csrf -> csrf.disable()) // Deshabilitar CSRF ya que vamos a utilizar el jwt
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))// para que no utilice  sesiones  y  no utilice el estado
                .addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationConfiguration.getAuthenticationManager()));

        return http.build();
    }

}
*/