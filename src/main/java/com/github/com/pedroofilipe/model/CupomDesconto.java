package com.github.com.pedroofilipe.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CupomDesconto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "valordesconto")
    private float valorDesconto;
    @Column(length = 10)
    private String descricao;

    public CupomDesconto(){

    }

    public CupomDesconto(float valorDesconto, Date dataValidade, String descricao){
        this.valorDesconto = valorDesconto;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(float valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
