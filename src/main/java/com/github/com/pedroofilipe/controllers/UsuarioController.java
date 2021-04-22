package com.github.com.pedroofilipe.controllers;

import com.github.com.pedroofilipe.model.Usuario;
import com.github.com.pedroofilipe.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.OK);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<?> buscar(Pageable pageable) {
        return new ResponseEntity<>(usuarioRepository.findAll(pageable), HttpStatus.OK);
    }

    @PutMapping("/alterar")
    public ResponseEntity<?> alterar(Pageable pageable) {
//        return new ResponseEntity<>(usuarioRepository.findAll(pageable), HttpStatus.OK);
        return null;
    }

}
