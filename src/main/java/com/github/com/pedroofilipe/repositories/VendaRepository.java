package com.github.com.pedroofilipe.repositories;

import com.github.com.pedroofilipe.model.Venda;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VendaRepository extends PagingAndSortingRepository<Venda, Integer> {

	Page<Venda> findByUsuario_Id(Pageable pageable, int usuarioId);
	
}
