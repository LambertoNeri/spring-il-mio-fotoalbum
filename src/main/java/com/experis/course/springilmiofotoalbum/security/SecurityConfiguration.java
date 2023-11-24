package com.experis.course.springilmiofotoalbum.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

  // configurazione su comee avere uno UserDetailsService
  @Bean
  public DatabaseUserDetailsService userDetailsService() {
    return new DatabaseUserDetailsService();
  }

  // configurazione su come avere un PasswordEncoder
  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  //configurazione dell'AuthenticationProvider
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService());
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests()
        .requestMatchers("/photos", "photos/**").hasAnyAuthority("ADMIN")
        .requestMatchers("/**").permitAll()
        .and().formLogin().and().logout();
    http.csrf().disable();
    return http.build();
  }
}
