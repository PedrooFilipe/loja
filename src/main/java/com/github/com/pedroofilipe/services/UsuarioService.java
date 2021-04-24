package com.github.com.pedroofilipe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.com.pedroofilipe.model.Usuario;
import com.github.com.pedroofilipe.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	public Usuario autenticar(Usuario usuarioAutenticar) {
		Usuario usuario = usuarioRepository.findByEmail(usuarioAutenticar.getEmail()).orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		if(usuarioAutenticar.getSenha().equals(usuario.getSenha())) {
			usuario.setToken(TokenService.generateToken(usuario));
			return usuario;
		}else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
	}

}
