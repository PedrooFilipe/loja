package com.github.com.pedroofilipe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.com.pedroofilipe.model.Carrinho;
import com.github.com.pedroofilipe.model.ItemCarrinho;
import com.github.com.pedroofilipe.repositories.CarrinhoRepository;
import com.github.com.pedroofilipe.services.CarrinhoService;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {

    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Carrinho carrinho) {
        return new ResponseEntity<>(carrinhoRepository.save(carrinho), HttpStatus.OK);
    }

    @PostMapping("/adicionar-item")
    public ResponseEntity<?> adicionarItem(@RequestBody ItemCarrinho itemCarrinho) {
        return new ResponseEntity<>(carrinhoService.adicionarItem(itemCarrinho), HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscar(Pageable pageable) {
        return new ResponseEntity<>(carrinhoService.listarTodos(pageable), HttpStatus.OK);
    }
//
//    @GetMapping("/retornar/{id}")
//    public ResponseEntity<?> retornar(@PathVariable Integer id) {
//        return new ResponseEntity<>(vendaRepository.findById(id), HttpStatus.OK);
//    }

}
