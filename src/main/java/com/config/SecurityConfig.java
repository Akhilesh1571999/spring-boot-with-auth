package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/admin/save","admin/update","admin/delete/{id}","/user/getAll","/user/{id}")
		.authenticated().antMatchers("/users/register").permitAll().and().formLogin().and().httpBasic();
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordencoder(){
	return new BCryptPasswordEncoder();
	}
}
