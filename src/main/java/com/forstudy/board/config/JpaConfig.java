package com.forstudy.board.config;

import com.forstudy.board.dto.BoardPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext())    // security 가지고있는 class
                .map(SecurityContext::getAuthentication)                        // Authentication mapping
                .filter(Authentication::isAuthenticated)                        // 인증여부 확인
                .map(Authentication::getPrincipal)                              // 인증정보 매핑
//                .map(x -> (BoardPrincipal) x)
                .map(BoardPrincipal.class::cast)
                .map(BoardPrincipal::getUsername);
    }

}
