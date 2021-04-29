package com.github.com.pedroofilipe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import com.github.com.pedroofilipe.model.Carrinho;
import com.github.com.pedroofilipe.model.Usuario;
import com.github.com.pedroofilipe.repositories.CarrinhoRepository;
import com.github.com.pedroofilipe.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{
	
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	CarrinhoRepository carrinhoRepository;
	
	public Usuario cadastrar(Usuario usuario) {
		usuario = usuarioRepository.save(usuario);
		carrinhoRepository.save(new Carrinho(usuario));
		
		return usuario;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Usuário e/ou senha não encontrados"));
		
		return User.builder().username(usuario.getEmail()).password(usuario.getSenha()).roles(usuario.getTipoUsuario().toString()).build();
	}
	
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		InMemoryUserDetailsManager uds = new InMemoryUserDetailsManager();
//		
//		UserDetails userAdmin = User.withUsername("admin").password("12345").authorities("ROLE_ADMINISTRADOR").build();
//		
//		UserDetails userCliente = User.withUsername("cliente").password("1235").authorities("ROLE_CLIENTE").build();
//		
//		uds.createUser(userAdmin);
//		uds.createUser(userCliente);
//		
//		return uds;
//	}	
}
