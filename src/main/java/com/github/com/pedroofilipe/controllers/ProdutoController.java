package com.github.com.pedroofilipe.controllers;

import com.github.com.pedroofilipe.model.Produto;
import com.github.com.pedroofilipe.repositories.ProdutoRepository;
import com.github.com.pedroofilipe.services.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    ProdutoRepository produtoRepository;
    ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository, ProdutoService produtoService){
        this.produtoRepository = produtoRepository;
        this.produtoService = produtoService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Produto produto) {
        return new ResponseEntity<>(produtoRepository.save(produto), HttpStatus.CREATED);
    }

    @GetMapping("/buscar-por-descricao")
    public ResponseEntity<?> buscarPorDescricao(@RequestParam String descricao) {
        return new ResponseEntity<>(produtoRepository.findByDescricao(descricao).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado")), HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscar(Pageable pageable) {
        return new ResponseEntity<>(produtoRepository.findAll(pageable), HttpStatus.OK);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterar(@PathVariable Integer id, @RequestBody Produto produtoAtualizado) {
    	return new ResponseEntity<>(produtoService.alterar(id, produtoAtualizado), HttpStatus.OK);
    }
}
