package com.github.com.pedroofilipe.repositories;

import com.github.com.pedroofilipe.model.CupomDesconto;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CupomDescontoRepository extends PagingAndSortingRepository<CupomDesconto, Integer> {

    Optional<CupomDesconto> findByDescricao(String descricao);

    boolean existsByDescricao(String descricao);
}
