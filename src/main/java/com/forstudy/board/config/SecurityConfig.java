package com.forstudy.board.config;

import com.forstudy.board.dto.BoardPrincipal;
import com.forstudy.board.dto.UserAccountDto;
import com.forstudy.board.repository.UserAccountRepository;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //권한 검사
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers(
                                HttpMethod.GET,
                                "/",
                                "/articles",
                                "/articles/search-hashtag"
                        ).permitAll()   // 허용부분
                        .anyRequest().authenticated()
                )
                .formLogin().and()
                .logout()
                .logoutSuccessUrl("/").and()
                .build();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        //security 검사 제외 :: static resource, css, js ...
//        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//    }

    @Bean
    public UserDetailsService userDetailsService(UserAccountRepository userAccountRepository) {
        //유저 권한 확인
        return username -> userAccountRepository
                .findById(username)
                .map(UserAccountDto::from)
                .map(BoardPrincipal::from)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다 - username : " + username));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // pw Encoding set
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
