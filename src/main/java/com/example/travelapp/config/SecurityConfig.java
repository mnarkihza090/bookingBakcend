package com.example.travelapp.config;

import com.example.travelapp.filters.JwtAuthenticationEntryPoint;
import com.example.travelapp.filters.JwtRequestFilter;
import com.example.travelapp.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    private final String[] publicUrl = {
            "/",
            "/api/**",
            "/api/auth/**",
            "/api/hotels",
            "/home",
            "/verify",
            "/*.css",
            "/*.js",
            "/*.js.map",
            "/images/**",
            "/uploads/**",
            "/uploads/hotels",
            "/uploads/users",
            "/resources/**",
            "/static/**",
            "/css/**",
            "/icon/**",
            "/js/**",
            "/images/**",
            "/favicon.ico",
            "/v2/api-docs",
            "/error",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(authenticationEntryPoint))
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(publicUrl).permitAll()
                                .requestMatchers("/api/user/profile").authenticated()
                                .requestMatchers("/api/auth/profile/my-booking").authenticated()
                        .anyRequest().authenticated()
                        );

        // Add our custom JWT security filter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /*@Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManager = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManager.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManager.build();
    }*/
   @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}



















