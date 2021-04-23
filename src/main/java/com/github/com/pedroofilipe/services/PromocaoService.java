package com.github.com.pedroofilipe.services;

import com.github.com.pedroofilipe.enums.TipoPromocao;
import com.github.com.pedroofilipe.model.Categoria;
import com.github.com.pedroofilipe.model.Promocao;
import com.github.com.pedroofilipe.repositories.CategoriaRepository;
import com.github.com.pedroofilipe.repositories.PromocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PromocaoService {

    @Autowired
    private PromocaoRepository promocaoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Promocao cadastrar(Promocao promocao){

        Promocao promocaoSalva = promocaoRepository.save(promocao);

        if(promocao.getTipoPromocao() == TipoPromocao.PRODUTO){
            for(Categoria item : promocao.getCategorias()){
                Optional<Categoria> categoria = categoriaRepository.findById(item.getId());
                categoriaRepository.save(new Categoria(categoria.get().getId(), categoria.get().getDescricao(), promocaoSalva));
            }
        }

        return promocaoSalva;
    }

}
