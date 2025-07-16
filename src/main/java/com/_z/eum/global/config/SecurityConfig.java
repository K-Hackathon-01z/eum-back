package com._z.eum.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Swagger 테스트 편의를 위해 CSRF 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .anyRequest().permitAll() // 개발 중에는 permitAll(), 운영 시 authenticated() 권장
                )
                .formLogin(form -> form.disable())  // 기본 로그인 폼 비활성화
                .httpBasic(basic -> basic.disable()); // HTTP Basic 인증 비활성화

        return http.build();
    }
}
