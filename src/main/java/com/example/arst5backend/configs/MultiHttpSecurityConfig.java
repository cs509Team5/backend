package com.example.arst5backend.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MultiHttpSecurityConfig {
    @Bean

    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().permitAll() // Allow all requests without authentication
                )
                .csrf().disable() // Optionally disable CSRF protection, depending on your requirements
                .httpBasic().disable() // Disable HTTP Basic authentication
                .formLogin().disable(); // Disable form login

        return http.build();
    }
}