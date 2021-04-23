package com.github.com.pedroofilipe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descricao;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "promocao_id", nullable = true)
    private Promocao promocao;

    public Categoria(){

    }

    public Categoria(int id, String descricao, Promocao promocao){
        this.id = id;
        this.descricao = descricao;
        this.promocao = promocao;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }
}
