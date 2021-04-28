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
    @Column(name= "valoraplicadopromocaocarrinho")
    private float valorAplicadoPromocaoCarrinho;
    @OneToMany(mappedBy = "carrinho")
    @JsonManagedReference
    private List<ItemCarrinho> itemCarrinhos;
    @ManyToMany
    @JoinTable(name="carrinho_has_promocao", joinColumns = {@JoinColumn(name="carrinho_id")}, inverseJoinColumns = {@JoinColumn(name="promocao_id")})
    private List<Promocao> promocoes;
    @OneToOne
    private Usuario usuario;
    
    public Carrinho() {
    	
    }
    
    public Carrinho(Usuario usuario) {
    	this.usuario = usuario;
    }
    

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

	public float getValorAplicadoPromocaoCarrinho() {
		return valorAplicadoPromocaoCarrinho;
	}

	public void setValorAplicadoPromocaoCarrinho(float valorAplicadoPromocaoCarrinho) {
		this.valorAplicadoPromocaoCarrinho = valorAplicadoPromocaoCarrinho;
	}

	public List<Promocao> getPromocoes() {
		return promocoes;
	}

	public void setPromocoes(List<Promocao> promocoes) {
		this.promocoes = promocoes;
	}    
}
