package com.github.com.pedroofilipe.controllers;

import com.github.com.pedroofilipe.model.Produto;
import com.github.com.pedroofilipe.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Produto produto) {
        return new ResponseEntity<>(produtoRepository.save(produto), HttpStatus.OK);
    }

    @GetMapping("/buscar-por-descricao")
    public ResponseEntity<?> buscarPorDescricao(@RequestParam String descricao) {
        return new ResponseEntity<>(produtoRepository.findByDescricao(descricao).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND)), HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscar(Pageable pageable) {
        return new ResponseEntity<>(produtoRepository.findAll(pageable), HttpStatus.OK);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterar(@PathVariable Integer id, @RequestBody Produto produtoAtualizado) {

        return new ResponseEntity<>(produtoRepository.findById(id).map(produto -> {
            produtoAtualizado.setId(produto.getId());
            return produtoRepository.save(produtoAtualizado);
            }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND)), HttpStatus.OK);
    }
}
