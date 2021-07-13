package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.security.oauth.GitHubOauthAuthenticationFilter;

@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringBootSemesterWorkApplication {

    @Bean
    public GitHubOauthAuthenticationFilter gitHubOauthAuthenticationFilter() {
        return new GitHubOauthAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSemesterWorkApplication.class, args);
    }

}
