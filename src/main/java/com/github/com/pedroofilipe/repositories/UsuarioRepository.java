package com.github.com.pedroofilipe.repositories;


import com.github.com.pedroofilipe.model.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Integer> {

}
