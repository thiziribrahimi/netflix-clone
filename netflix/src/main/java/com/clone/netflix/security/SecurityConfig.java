package com.clone.netflix.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.clone.netflix.service.CustomUserDetailsService;

@EnableMethodSecurity(securedEnabled = true) // Active les annotations @Secured
@Configuration
public class SecurityConfig {


    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Déclare un Bean pour crypter les mots de passe avec BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configure les règles d'accès aux endpoints
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Désactive la protection CSRF pour simplifier les tests
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/movies/**").permitAll() // Accessible à tous (lecture des films)
                .requestMatchers("/admin/**").hasRole("ADMIN") // Accessible uniquement aux admins
                .anyRequest().authenticated()
            )
            .httpBasic(); // Authentification basique pour tester

        return http.build();
    }

    // Configure le gestionnaire d'authentification
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService) // Utilise le service CustomUserDetailsService
                .passwordEncoder(passwordEncoder()) // Utilise BCrypt pour vérifier les mots de passe
                .and()
                .build();
    }
}