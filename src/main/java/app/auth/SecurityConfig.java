package app.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(c -> c.disable());
        http.csrf(c -> c.disable());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/").permitAll());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/users/**").permitAll());

        //disactivated session because we are using in this case JWT so whit stateless it mean without state/session 
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); 

        return http.build(); // when you finish all the configuration settings is important return http.build
    }
}