package com.github.com.pedroofilipe.controllers;

import com.github.com.pedroofilipe.model.TipoUsuario;
import com.github.com.pedroofilipe.repositories.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipo-usuarios")
public class TipoUsuarioController {

    TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    public TipoUsuarioController(TipoUsuarioRepository tipoUsuarioRepository){
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody TipoUsuario tipoUsuario) {
        return new ResponseEntity<>(tipoUsuarioRepository.save(tipoUsuario), HttpStatus.OK);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<?> buscar(Pageable pageable) {
        return new ResponseEntity<>(tipoUsuarioRepository.findAll(pageable), HttpStatus.OK);
    }

}
