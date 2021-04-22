package com.github.com.pedroofilipe.model;

import javax.persistence.*;

@Entity
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Produto produto;
    private float quantidade;
    @Column(name = "valortotal")
    private float valorTotal;
    @ManyToOne
    @JoinColumn(name = "venda_id", nullable = false)
    private Venda venda;


    public ItemVenda(){

    }

    public ItemVenda(Produto produto, float quantidade, float valorTotal, Venda venda){
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.venda = venda;
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

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
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
}
