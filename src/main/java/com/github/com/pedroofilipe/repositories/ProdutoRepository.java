package com.github.com.pedroofilipe.repositories;

import com.github.com.pedroofilipe.model.Produto;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Integer> {

    Optional<Produto> findByDescricao(String descricao);

}
