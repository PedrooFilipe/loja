package com.github.com.pedroofilipe.repositories;

import com.github.com.pedroofilipe.model.Carrinho;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarrinhoRepository extends PagingAndSortingRepository<Carrinho, Integer> {

	Optional<Carrinho> findByUsuario_Id(int usuarioId);
	
}
