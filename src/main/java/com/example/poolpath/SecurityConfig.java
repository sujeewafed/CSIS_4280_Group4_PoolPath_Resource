package com.example.poolpath;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
            .authorizeHttpRequests(auth -> auth
                // ✅ Allow Swagger UI and OpenAPI without auth
                .requestMatchers(
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-resources/**",
                    "/webjars/**",
                    "/configuration/**"
                ).permitAll()

                // ✅ Allow H2 Console (optional, for dev only)
                .requestMatchers("/h2-console/**").permitAll()

                // ✅ Secure your API routes
                .requestMatchers(HttpMethod.GET, "/api/poolpath/teams/**").hasAuthority("SCOPE_poolpath:read")
                .requestMatchers(HttpMethod.POST, "/api/poolpath/teams/**").hasAuthority("SCOPE_poolpath:admin")

                // 🔒 All other requests require authentication
                .anyRequest().authenticated()
            )
            // ✅ OAuth2 Resource Server
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))

            // ✅ Required for H2 Console (frames + CSRF)
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions().disable())

            .build();
    }
}
