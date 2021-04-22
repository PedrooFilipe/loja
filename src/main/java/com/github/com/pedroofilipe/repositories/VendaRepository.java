package com.github.com.pedroofilipe.repositories;

import com.github.com.pedroofilipe.model.Venda;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface VendaRepository extends PagingAndSortingRepository<Venda, Integer> {

}
