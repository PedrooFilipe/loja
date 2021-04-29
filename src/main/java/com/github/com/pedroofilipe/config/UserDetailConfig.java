package com.github.com.pedroofilipe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailConfig {

	
	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager uds = new InMemoryUserDetailsManager();
		
		UserDetails userAdmin = User.withUsername("admin").password("12345").authorities("ROLE_ADMINISTRADOR").build();
		
		UserDetails userCliente = User.withUsername("cliente").password("1235").authorities("ROLE_CLIENTE").build();
		
		uds.createUser(userAdmin);
		uds.createUser(userCliente);
		
		return uds;
	}	
	
}
