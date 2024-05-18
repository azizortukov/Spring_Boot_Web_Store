package uz.anas.web_store.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import uz.anas.web_store.error.LoginHandler;
import uz.anas.web_store.config.CustomUserDetailsService;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final LoginHandler loginHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(manager -> {
            manager
                    .requestMatchers("/","/login", "/css/**", "/basket", "/basket/**", "/file/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/file/**").denyAll()
                    .anyRequest()
                    .authenticated();
        });
        http.formLogin(manager -> {
            manager
                    .loginPage("/login")
                    .failureHandler(loginHandler);
        });
        http.rememberMe(manager -> {
            manager
                    .rememberMeParameter("remember-me")
                    .tokenValiditySeconds(60 * 60 * 24 * 30);
        });

        http.userDetailsService(customUserDetailsService);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
