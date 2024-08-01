package org.example.websocketchat2.config;

import org.example.websocketchat2.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/userregform", "/userreg", "/css/**", "/js/**").permitAll()
//                        .requestMatchers("/chat", "/chat/**", "/ws/**").authenticated()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(Customizer.withDefaults())
//                .userDetailsService(customUserDetailsService)
//                .csrf(csrf -> csrf.disable());
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/userregform", "/userreg", "/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/loginform")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/welcome", true)
                        .failureUrl("/loginerror")
                        .permitAll()
                )
                .userDetailsService(customUserDetailsService)
                .csrf(csrf -> csrf.disable());

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}