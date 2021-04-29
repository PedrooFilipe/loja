package com.github.com.pedroofilipe.controllers;

import com.github.com.pedroofilipe.dto.CarrinhoDto;
import com.github.com.pedroofilipe.dto.UsuarioDto;
import com.github.com.pedroofilipe.model.Usuario;
import com.github.com.pedroofilipe.repositories.UsuarioRepository;
import com.github.com.pedroofilipe.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
    UsuarioRepository usuarioRepository;
	@Autowired
	UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(UsuarioDto.toDto(usuarioService.cadastrar(usuario)), HttpStatus.CREATED);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<?> buscar(Pageable pageable) {
        return new ResponseEntity<>(usuarioRepository.findAll(pageable), HttpStatus.OK);
    }

}
