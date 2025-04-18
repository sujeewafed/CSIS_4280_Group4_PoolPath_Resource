package com.example.poolpath;

/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

*/
// @Configuration
public class SecurityConfig {
	/*
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http.authorizeHttpRequests(

				authorize -> authorize.requestMatchers(HttpMethod.GET, "/api/poolpath/teams/**")
						.hasAuthority("SCOPE_poolpath:read").requestMatchers(HttpMethod.POST, "/api/poolpath/teams/**")
						.hasAuthority("SCOPE_poolpath:admin").anyRequest().authenticated()

		).oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())).build();

	}
	*/
}
