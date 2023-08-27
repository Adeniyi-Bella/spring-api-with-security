package io.javatech.api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.Customizer.*;
import static org.springframework.security.config.http.SessionCreationPolicy.*;


//allows you to configure security settings for web-based endpoints in your application.
@EnableWebSecurity
//automatically generates a constructor with required arguments for the annotated class
@RequiredArgsConstructor
//This annotation enables global method-level security for your Spring application.
// The prePostEnabled = true parameter indicates that you want to enable support for using the @PreAuthorize
// and @PostAuthorize annotations on methods. These annotations allow you to specify authorization rules before
// and after a method is executed.
@EnableGlobalMethodSecurity(prePostEnabled = true) //allow security at a method level
public class WebSecurityConfiguration {
    private final AccountAuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider); //define an authentication provider



        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/accounts/**").permitAll()//permit all requests to this endpoint
                .requestMatchers("/**").authenticated() //requests must come from an authenticated user
                .anyRequest()
                .hasAnyRole("USER", "ADMIN")
                .and()
                .httpBasic(withDefaults()) //a simple way of http authentication without token and the rest
                .sessionManagement()
                .sessionCreationPolicy(STATELESS);
                return http.build();
    }
}