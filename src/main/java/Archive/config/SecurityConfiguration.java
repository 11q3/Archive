package Archive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth ->
                                auth
                                        .requestMatchers(
                                                "/",
                                                "/docks",
                                                "/register",
                                                "/login"
                                                )
                                            .permitAll()
                                        .requestMatchers("/account").authenticated()
                                        .requestMatchers("/static/css/**",
                                                         "/static/images/**").permitAll())
                .formLogin(form -> form.loginPage("/login"))
                .logout(l -> l.logoutSuccessUrl("/login?logout").permitAll());

        return http.build();
    }
}