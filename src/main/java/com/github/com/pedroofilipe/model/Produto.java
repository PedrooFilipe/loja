package com.github.com.pedroofilipe.model;

import javax.persistence.*;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descricao;
    @Column(name = "precovenda")
    private float precoVenda;
    private boolean inativo;

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

    public boolean isInativo() {
        return inativo;
    }

    public void setInativo(boolean inativo) {
        this.inativo = inativo;
    }
}
