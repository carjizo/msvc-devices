package org.sisvir.msvc.devices;


import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/devices/getAll").permitAll()
                .antMatchers(HttpMethod.GET, "/devices/{id}").hasAnyAuthority("SCOPE_read", "SCOPE_write")
                .antMatchers(HttpMethod.GET, "/devices/mac/{mac}").hasAnyAuthority("SCOPE_read", "SCOPE_write")
                .antMatchers(HttpMethod.POST, "/devices/create").hasAuthority("SCOPE_write")
                .antMatchers(HttpMethod.PUT, "/devices/update/{id}").hasAuthority("SCOPE_write")
                .antMatchers(HttpMethod.DELETE, "/devices/delete/{id}").hasAuthority("SCOPE_write")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .oauth2Client(withDefaults())
                .oauth2ResourceServer().jwt();

        return http.build();
    }
}
