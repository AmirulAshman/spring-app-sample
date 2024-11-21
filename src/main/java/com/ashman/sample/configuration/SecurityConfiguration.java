package com.ashman.sample.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.ashman.sample.configuration.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // @Bean
    // public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
    // UserDetails user = User.withUsername("user").password(passwordEncoder.encode("password")).roles("USER").build();

    // UserDetails admin =
    // User.withUsername("admin").password(passwordEncoder.encode("admin")).roles("ADMIN", "USER").build();

    // return new InMemoryUserDetailsManager(user, admin);
    // }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http //
                .authorizeHttpRequests(request -> request.requestMatchers("/login") //
                        .permitAll() //
                        .anyRequest() //
                        .authenticated()) //
                .csrf(csrf -> csrf.disable()) //
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class) //
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }
}
