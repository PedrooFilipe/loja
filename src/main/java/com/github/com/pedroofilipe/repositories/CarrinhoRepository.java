package com.github.com.pedroofilipe.repositories;

import com.github.com.pedroofilipe.model.Carrinho;
import com.github.com.pedroofilipe.model.Produto;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CarrinhoRepository extends PagingAndSortingRepository<Carrinho, Integer> {

}
