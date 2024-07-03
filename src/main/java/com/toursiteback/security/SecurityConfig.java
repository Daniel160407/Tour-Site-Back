package com.toursiteback.security;

import com.toursiteback.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtUtils jwtUtils;

    @Autowired
    public SecurityConfig(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/adminpanel", "/tours", "/*.html", "/assets/*.css", "/assets/*.js", "/images/*.*", "/fonts/*.ttf", "/svg/*.svg").permitAll()
                        .requestMatchers(HttpMethod.POST, "/tours/adminpanel/login").permitAll()
                        .requestMatchers("/tours/tour/*").permitAll()
                        .requestMatchers("/tours/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/tours/messenger/login").permitAll()
                        .requestMatchers("/tours/adminpanel/messenger/messages").permitAll()
                        .requestMatchers(HttpMethod.POST, "/statistics").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/statistics").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/statistics").permitAll()
                        .requestMatchers("/api/v3/auth").permitAll()
                        .requestMatchers("/api/v3/signup").permitAll()
                        .requestMatchers("/messenger").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
