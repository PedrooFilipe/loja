package com.github.com.pedroofilipe.services;

import com.github.com.pedroofilipe.enums.TipoPromocao;
import com.github.com.pedroofilipe.model.Categoria;
import com.github.com.pedroofilipe.model.Promocao;
import com.github.com.pedroofilipe.repositories.CategoriaRepository;
import com.github.com.pedroofilipe.repositories.PromocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PromocaoService {

    private PromocaoRepository promocaoRepository;
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    public PromocaoService(PromocaoRepository promocaoRepository, CategoriaRepository categoriaRepository) {
    	this.promocaoRepository = promocaoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Promocao cadastrar(Promocao promocao){

    	promocao = promocaoRepository.save(promocao);

        if(promocao.getTipoPromocao() == TipoPromocao.PRODUTO){
            for(Categoria item : promocao.getCategorias()){
                Categoria categoria = categoriaRepository.findById(item.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada!"));
                categoriaRepository.save(new Categoria(categoria.getId(), categoria.getDescricao(), promocao));
            }
        }

        return promocao;
    }
}
