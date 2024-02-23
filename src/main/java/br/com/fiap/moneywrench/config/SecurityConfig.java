package br.com.fiap.moneywrench.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	AuthenticationFilter authenticationFilter;

	@Bean
	SecurityFilterChain web(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((authorize) -> authorize
						.requestMatchers("/login", "/usuario").permitAll()
						.anyRequest().authenticated())
				.csrf(csrf -> csrf.disable())
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
				;

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
