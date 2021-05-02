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
		.antMatchers("/usuarios/cadastrar").permitAll()
		.antMatchers(
			"/produtos/buscar",
			"/produtos/retornar",
			"/carrinhos/adicionar-item",
			"/carrinhos/remover-item",
			"/vendas/cadastrar",
			"/vendas/buscar-por-cliente",
			"/carrinhos/buscar-por-usuario/**")
		.authenticated()
		.antMatchers(
				"/carrinho/buscar",
				"/produtos/cadastrar",
				"/promocoes/cadastrar",
				"/promocoes/buscar",
				"/promocoes/cadastrar",
				"/categorias/cadastrar",
				"/categorias/buscar",
				"/vendas/buscar",
				"/usuarios/buscar"
				).hasRole("ADMINISTRADOR");
	}
	
}
