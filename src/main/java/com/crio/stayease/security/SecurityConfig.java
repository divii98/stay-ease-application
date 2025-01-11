package com.crio.stayease.security;

import com.crio.stayease.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PutMapping;

@Configuration
public class SecurityConfig {
    private final JwtAuthenticationFilter authenticationFilter;

    public  SecurityConfig(JwtAuthenticationFilter  authenticationFilter){
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/user/login").permitAll()
                        .requestMatchers("/auth/token").permitAll()
                        .requestMatchers("/user/register").permitAll()
                        .requestMatchers("/hotel/add").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/hotel/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/hotel/*").hasRole("HOTEL_MANAGER")
                        .requestMatchers("/hotel/*/book").hasRole("CUSTOMER")
                        .requestMatchers("/hotel/bookings/*").hasRole("HOTEL_MANAGER")
                        .anyRequest().authenticated()
                ).exceptionHandling(ex -> ex.authenticationEntryPoint(new CustomAuthenticationEntryPoint()))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
                return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new CustomUserDetailService(userRepository);
    }

}
