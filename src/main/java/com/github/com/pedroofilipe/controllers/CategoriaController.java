package com.github.com.pedroofilipe.controllers;

import com.github.com.pedroofilipe.model.Categoria;
import com.github.com.pedroofilipe.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Categoria categoria) {
        return new ResponseEntity<>(categoriaRepository.save(categoria), HttpStatus.OK);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<?> buscar(Pageable pageable) {
        return new ResponseEntity<>(categoriaRepository.findAll(pageable), HttpStatus.OK);
    }

}
