package com.github.com.pedroofilipe.model;

import javax.persistence.*;

@Entity
public class CupomDescontoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Usuario usuario;
    @OneToOne
    private CupomDesconto cupomDesconto;
}
