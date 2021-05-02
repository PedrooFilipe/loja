package com.github.com.pedroofilipe.controllers;

import com.github.com.pedroofilipe.model.Promocao;
import com.github.com.pedroofilipe.repositories.PromocaoRepository;
import com.github.com.pedroofilipe.services.PromocaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promocoes")
public class PromocaoController {
	
	private PromocaoRepository promocaoRepository;
	private PromocaoService promocaoService;

	@Autowired
	public PromocaoController(PromocaoRepository promocaoRepository, PromocaoService promocaoService) {
		this.promocaoRepository = promocaoRepository;
	    this.promocaoService = promocaoService;
	}

    @PostMapping("/cadastrar")
    private ResponseEntity<Promocao> cadastrar(@RequestBody Promocao promocao){
        return new ResponseEntity<>(promocaoService.cadastrar(promocao), HttpStatus.CREATED);
    }
    
    @GetMapping("/buscar")
    private ResponseEntity<?> buscar(Pageable pageable){
        return new ResponseEntity<>(promocaoRepository.findAll(pageable), HttpStatus.OK);
    }
}
