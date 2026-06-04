package com.marcos.vendas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			//Configuração específica para o console do h2 abrir os seus componentes visuais desde que seja da mesma máquina
			.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
			.authorizeHttpRequests(authorize -> authorize
					.requestMatchers("/h2-console/**").permitAll()
					.anyRequest().authenticated())
			.httpBasic(basic -> {});
		
		return http.build();
	}

}
