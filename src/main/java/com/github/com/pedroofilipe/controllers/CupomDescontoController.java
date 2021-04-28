package com.github.com.pedroofilipe.controllers;

import com.github.com.pedroofilipe.helper.MensagemRetorno;
import com.github.com.pedroofilipe.model.CupomDesconto;
import com.github.com.pedroofilipe.repositories.CupomDescontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/cupons")
public class CupomDescontoController {

    CupomDescontoRepository cupomDescontoRepository;

    @Autowired
    public CupomDescontoController (CupomDescontoRepository cupomDescontoRepository){
        this.cupomDescontoRepository = cupomDescontoRepository;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody CupomDesconto cupom) {
        return new ResponseEntity<>(cupomDescontoRepository.save(cupom), HttpStatus.OK);
    }

    @GetMapping("/buscar-por-descricao")
    public ResponseEntity<?> buscarPorDescricao(@RequestParam String descricao) {
        return new ResponseEntity<>(cupomDescontoRepository.findByDescricao(descricao).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND)), HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscar(Pageable pageable) {
        return new ResponseEntity<>(cupomDescontoRepository.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/aplicar")
    public ResponseEntity<?> aplicarDesconto(@RequestParam String descricao){

        boolean cupomExiste = cupomDescontoRepository.existsByDescricao(descricao);
        MensagemRetorno mensagemRetorno = null;

        if(cupomExiste) {
            mensagemRetorno = new MensagemRetorno("Cupom validado com sucesso!", "Sucesso");
        }else{
            mensagemRetorno = new MensagemRetorno("Cupom invalido!", "Erro");
        }

        return new ResponseEntity<>(mensagemRetorno, HttpStatus.OK);
    }

}
