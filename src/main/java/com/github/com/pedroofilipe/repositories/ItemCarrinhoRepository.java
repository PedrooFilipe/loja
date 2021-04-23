package com.github.com.pedroofilipe.repositories;

import com.github.com.pedroofilipe.model.ItemCarrinho;
import com.github.com.pedroofilipe.model.Produto;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ItemCarrinhoRepository extends PagingAndSortingRepository<ItemCarrinho, Integer> {

}
