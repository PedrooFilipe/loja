package com.github.com.pedroofilipe.repositories;

import com.github.com.pedroofilipe.model.TipoUsuario;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface TipoUsuarioRepository extends PagingAndSortingRepository<TipoUsuario, Integer> {

}
