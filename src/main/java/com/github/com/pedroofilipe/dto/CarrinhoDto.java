package com.github.com.pedroofilipe.dto;

import java.util.List;

import com.github.com.pedroofilipe.model.Carrinho;
import com.github.com.pedroofilipe.model.ItemCarrinho;
import com.github.com.pedroofilipe.model.Promocao;

public class CarrinhoDto {
	
	private String nomeUsuario;
	private int idUsuario;
	private float valorTotal;
	private List<Promocao> promocoes;
	private List<ItemCarrinho> itensCarrinho;

	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public List<Promocao> getPromocoes() {
		return promocoes;
	}

	public List<ItemCarrinho> getItensCarrinho() {
		return itensCarrinho;
	}

	public CarrinhoDto() {
		
	}
	
	public CarrinhoDto(String nomeUsuario, int idUsuario, float valorTotal, List<Promocao> promocoes, List<ItemCarrinho> itensCarrinho) {
		this.nomeUsuario = nomeUsuario;
		this.idUsuario = idUsuario;
		this.valorTotal = valorTotal;
		this.promocoes = promocoes;
		this.itensCarrinho = itensCarrinho;
	}
	
	public static CarrinhoDto toDto(Carrinho carrinho) {
		return new CarrinhoDto(carrinho.getUsuario().getNome(), carrinho.getUsuario().getId(), carrinho.getValorTotal(), carrinho.getPromocoes(), carrinho.getItemCarrinhos());
	}
	

}
