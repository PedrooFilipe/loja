package com.github.com.pedroofilipe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descricao;
    @Column(name = "precovenda")
    private float precoVenda;
    @OneToOne
    private Categoria categoria;

    public Produto(){

    }

    public Produto(int id, String descricao, float precoVenda, Categoria categoria){
        this.id = id = id;
        this.descricao = descricao;
        this.precoVenda = precoVenda;
        this.categoria = categoria;
    }

    public Produto(int id, String descricao, float precoVenda){
        this.id = id = id;
        this.descricao = descricao;
        this.precoVenda = precoVenda;
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

    public float getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(float precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
