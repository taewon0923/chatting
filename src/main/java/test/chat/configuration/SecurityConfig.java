package test.chat.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import test.chat.service.UserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                // user 목록 admin만 가져올 수 있음
                .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                // task 등록 admin만 가능
                .requestMatchers(HttpMethod.GET, "/tasks/write").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/tasks").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/tasks").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/tasks").hasRole("ADMIN");

        return httpSecurity.build();
    }

}
