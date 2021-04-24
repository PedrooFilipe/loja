package com.github.com.pedroofilipe.repositories;


import com.github.com.pedroofilipe.model.Usuario;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Integer> {

	Optional<Usuario> findByEmail(String email);
	
}
