package com.example.travelapp.config;

import com.example.travelapp.filters.JwtRequestFilter;
import com.example.travelapp.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    private final String[] publicUrl = {
            "/",
            "/api/**",
            "/login",
            "/login/**",
            "/hotels",
            "/hotels/**",
            "/hotel-search/**",
            "/register/**",
            "/register",
            "/auth/**",
            "/src/**",
            "/verify",
            "/home",
            "/*.css",
            "/*.js",
            "/*.js.map",
            "/images/**",
            "/uploads/**",
            "/resources/**",
            "/static/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/favicon.ico",
            "/v2/api-docs",
            "/error",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(auth ->
            auth.requestMatchers(publicUrl)
                    .permitAll()
                    .anyRequest().authenticated()
                );

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.formLogin(formLogin ->
                        formLogin.loginPage("/login")
                                .failureUrl("/login?error")
                                .defaultSuccessUrl("/home", true)
                                .permitAll())
                .logout(logout ->
                        logout.permitAll()
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout")
                                .invalidateHttpSession(true)// Oturumun geçerliliğini sonlandır
                                .deleteCookies("JSESSIONID"));

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManager = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManager.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManager.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}



















