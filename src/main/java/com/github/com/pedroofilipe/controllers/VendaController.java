package com.github.com.pedroofilipe.controllers;

import com.github.com.pedroofilipe.model.Venda;
import com.github.com.pedroofilipe.services.VendaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    VendaService vendaService;

    @Autowired
    public VendaController(VendaService vendaService){
        this.vendaService = vendaService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestParam int usuarioId, @RequestBody Venda venda) {
        return new ResponseEntity<>(vendaService.cadastrar(usuarioId, venda), HttpStatus.CREATED);
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscar(Pageable pageable) {
        return new ResponseEntity<>(vendaService.listarTodos(pageable), HttpStatus.OK);
    }
    
    @GetMapping("/buscar-por-usuario")
    public ResponseEntity<?> buscar(@RequestParam int usuarioId, Pageable pageable) {
        return new ResponseEntity<>(vendaService.listarPorUsuario(pageable, usuarioId), HttpStatus.OK);
    }

    @GetMapping("/retornar/{id}")
    public ResponseEntity<?> retornar(@PathVariable Integer id) {
        return new ResponseEntity<>(vendaService.retornar(id), HttpStatus.OK);
    }

}
