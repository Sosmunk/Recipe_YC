package com.triangular.recipe_yc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain apiFilterChain(
            HttpSecurity httpSecurity, PersistentTokenBasedRememberMeServices rememberMeServices) throws Exception {
        return httpSecurity
                .antMatcher("/api/**")
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.anyRequest().permitAll())
                .logout(AbstractHttpConfigurer::disable)
                .rememberMe(rememberMe -> rememberMe.rememberMeServices(rememberMeServices))
                .build();
    }

    @Bean
    public PersistentTokenBasedRememberMeServices rememberMeServices(UserDetailsService userDetailsService,
                                                                     PersistentTokenRepository persistentTokenRepository) {
        var rememberMe = new PersistentTokenBasedRememberMeServices(
                "5aba297d-f633-423f-a6bf-662f78fa7834",
                userDetailsService,
                persistentTokenRepository
        );
        rememberMe.setCookieName("sess");
        rememberMe.setTokenValiditySeconds(2592000);
        rememberMe.setTokenLength(64);
        rememberMe.setSeriesLength(64);
        rememberMe.setAlwaysRemember(true);
        return rememberMe;
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
