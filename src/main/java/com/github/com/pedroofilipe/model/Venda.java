package com.github.com.pedroofilipe.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float valorTotal;
    private float valorTotalDesconto;
    private float valorDesconto;
    @OneToMany(mappedBy = "venda")
    private List<ItemVenda> itemVendas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<ItemVenda> getItemVendas() {
        return itemVendas;
    }

    public void setItemVendas(List<ItemVenda> itemVendas) {
        this.itemVendas = itemVendas;
    }
}
