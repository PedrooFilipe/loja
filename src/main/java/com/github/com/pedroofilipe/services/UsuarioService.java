package com.github.com.pedroofilipe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.com.pedroofilipe.dto.UsuarioDto;
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
	
	public Page<UsuarioDto> procurarTodos(Pageable pageable) {
		return usuarioRepository.findAll(pageable).map(usuario -> UsuarioDto.toDto(usuario));
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Usuário e/ou senha não encontrados"));
		
		return User.builder().username(usuario.getEmail()).password(usuario.getSenha()).roles(usuario.getTipoUsuario().toString()).build();
	}
}
