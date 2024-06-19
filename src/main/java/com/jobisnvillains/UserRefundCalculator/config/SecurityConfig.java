package com.jobisnvillains.UserRefundCalculator.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private static final String[] PERMIT_URL_ARRAY = {
            "/3o3/swagger-ui/**", "/3o3/swagger-ui.html",
            "/3o3/v3/api-docs/**", "/3o3/swagger-config",
            "/v3/api-docs/**", "/3o3/swagger.html",
            "/3o3/swagger-ui/index.html",
            "/szs/signup", "/szs/login", "/szs/scrap", "/szs/refund",
    };
    //private final JwtRequestFilter jwtRequestFilter;
    private final AuthenticationConfiguration authenticationConfiguration;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(session -> session.disable());

        //http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        http.csrf(csrfConfigurer -> csrfConfigurer.disable())
                .authorizeHttpRequests( request -> request.requestMatchers(PERMIT_URL_ARRAY)
                        .permitAll());
//                .antMatchers().permitAll()
//                .antMatchers("", "").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .sessionManagement().disable();

        return http.build();

//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
