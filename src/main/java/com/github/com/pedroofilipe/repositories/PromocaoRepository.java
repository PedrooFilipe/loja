package com.github.com.pedroofilipe.repositories;

import com.github.com.pedroofilipe.enums.TipoPromocao;
import com.github.com.pedroofilipe.model.Promocao;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PromocaoRepository extends PagingAndSortingRepository<Promocao, Integer> {

    Promocao findByCategorias_Id(int id);
    
    Promocao findBytipoPromocao(TipoPromocao tipoPromocao);
}
