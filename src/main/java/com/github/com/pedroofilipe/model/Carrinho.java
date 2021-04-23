package com.github.com.pedroofilipe.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float valorTotal;
    @OneToMany(mappedBy = "carrinho")
    @JsonManagedReference
    private List<ItemCarrinho> itemCarrinhos;
//    @ManyToMany(mappedBy = "carrinho")
//    @JsonManagedReference
//    private List<Promocao> promocoes;
    @OneToOne
    private Usuario usuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ItemCarrinho> getItemCarrinhos() {
        return itemCarrinhos;
    }

    public void setItemCarrinhos(List<ItemCarrinho> itemCarrinhos) {
        this.itemCarrinhos = itemCarrinhos;
    }

//    public List<Promocao> getPromocoes() {
//        return promocoes;
//    }
//
//    public void setPromocoes(List<Promocao> promocoes) {
//        this.promocoes = promocoes;
//    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
