package com.github.com.pedroofilipe.controllers;

import com.github.com.pedroofilipe.model.ItemVenda;
import com.github.com.pedroofilipe.model.Venda;
import com.github.com.pedroofilipe.repositories.ItemVendaRepository;
import com.github.com.pedroofilipe.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    VendaRepository vendaRepository;
    ItemVendaRepository itemVendaRepository;

    @Autowired
    public VendaController(VendaRepository vendaRepository, ItemVendaRepository itemVendaRepository){
        this.vendaRepository = vendaRepository;
        this.itemVendaRepository = itemVendaRepository;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Venda venda) {
        Venda vendaSalva = vendaRepository.save(venda);

        for(ItemVenda item : venda.getItemVendas()){
            itemVendaRepository.save(new ItemVenda(item.getProduto(), item.getQuantidade(), item.getValorTotal(), vendaSalva));
        }

        return new ResponseEntity<>(vendaSalva, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscar(Pageable pageable) {
        Page<Venda> vendas = vendaRepository.findAll(pageable);
        return new ResponseEntity<>(vendas, HttpStatus.OK);
    }

    @GetMapping("/retornar/{id}")
    public ResponseEntity<?> retornar(@PathVariable Integer id) {
        return new ResponseEntity<>(vendaRepository.findById(id), HttpStatus.OK);
    }

}
