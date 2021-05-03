package com.github.com.pedroofilipe.model;

import com.github.com.pedroofilipe.enums.TipoPagamento;

import javax.persistence.*;
import java.util.List;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name= "valortotal")
    private float valorTotal;
    @Column(name= "valortotaldesconto")
    private float valorTotalDesconto;
    @Column(name= "valordesconto")
    private float valorDesconto;
    @Column(name="itemvenda")
    @OneToMany(mappedBy = "venda")
    private List<ItemVenda> itemVendas;
    @OneToOne
    private Usuario usuario;
    @Column(name="tipopagamento")
    private TipoPagamento tipoPagamento;

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
}
