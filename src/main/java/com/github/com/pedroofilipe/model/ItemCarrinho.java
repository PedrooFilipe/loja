package com.github.com.pedroofilipe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class ItemCarrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Produto produto;
    private float quantidade;
    @Column(name = "valortotal")
    private float valorTotal;
    @Column(name = "valortotaldesconto")
    private float valorTotalDesconto;
    @Column(name = "valordesconto")
    private float valorDesconto;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "carrinho_id", nullable = false)
    private Carrinho carrinho;

    public ItemCarrinho(){

    }

    public ItemCarrinho(Produto produto, float quantidade, float valorTotal, Carrinho carrinho){
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.carrinho = carrinho;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public float getValorTotalDesconto() {
        return valorTotalDesconto;
    }

    public void setValorTotalDesconto(float valorTotalDesconto) {
        this.valorTotalDesconto = valorTotalDesconto;
    }

    public float getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(float valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }
}
