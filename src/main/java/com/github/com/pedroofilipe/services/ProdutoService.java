package com.github.com.pedroofilipe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.com.pedroofilipe.model.Produto;
import com.github.com.pedroofilipe.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	private ProdutoRepository produtoRepository;
	
	@Autowired
	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	public Produto alterar(int id, Produto produtoAtualizado) {
		Produto produto = produtoRepository.findById(id).map(prod -> {
			produtoAtualizado.setId(prod.getId());
			return produtoRepository.save(produtoAtualizado);
			}).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado"));
		
		return produto;
	}

}
