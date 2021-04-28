package com.github.com.pedroofilipe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/api/usuarios/cadastrar").permitAll()
		.antMatchers("/api/**").hasRole("ADMINISTRADOR")
		.antMatchers(
			"/api/produtos/buscar", 
			"/api/carrinhos/adicionar-item",
			"/api/carrinhos/remover-item",
			"/api/vendas/cadastrar",
			"/api/vendas/buscar-por-cliente")
		.authenticated();
	}
	
}
