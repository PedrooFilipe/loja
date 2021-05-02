package com.github.com.pedroofilipe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.com.pedroofilipe.dto.CarrinhoDto;
import com.github.com.pedroofilipe.model.ItemCarrinho;
import com.github.com.pedroofilipe.services.CarrinhoService;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping("/adicionar-item")
    public ResponseEntity<?> adicionarItem(@RequestBody ItemCarrinho itemCarrinho, @RequestParam int usuarioId) {
        return new ResponseEntity<>(CarrinhoDto.toDto(carrinhoService.adicionarItem(itemCarrinho, usuarioId)), HttpStatus.OK);
    }
    
    @PostMapping("/remover-item")
    public ResponseEntity<?> removerItem(@RequestBody ItemCarrinho itemCarrinho) {
        return new ResponseEntity<>(CarrinhoDto.toDto(carrinhoService.removerItem(itemCarrinho)), HttpStatus.OK);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<?> buscar(Pageable pageable) {
        return new ResponseEntity<>(carrinhoService.listarTodos(pageable), HttpStatus.OK);
    }

}
