package com.github.com.pedroofilipe.dto;

import com.github.com.pedroofilipe.model.ItemCarrinho;

public class ItemCarrinhoDto {
	
	private int produtoId;
	private String produtoNome;
	private float quantidade;
	private float valorTotal;
	private float valorDesconto;
	private float valorTotalDesconto;
	
	
	public int getProdutoId() {
		return produtoId;
	}
	public String getProdutoNome() {
		return produtoNome;
	}
	public float getQuantidade() {
		return quantidade;
	}
	public float getValorTotal() {
		return valorTotal;
	}
	public float getValorDesconto() {
		return valorDesconto;
	}
	public float getValorTotalDesconto() {
		return valorTotalDesconto;
	}
	
	public ItemCarrinhoDto() {
		
	}
	
	public ItemCarrinhoDto(int produtoId, String produtoNome, float quantidade, float valorTotal, float valorDesconto, float valorTotalDesconto) {
		this.produtoId = produtoId;
		this.produtoNome = produtoNome;
		this.quantidade = quantidade;
		this.valorTotal = valorTotal;
		this.valorDesconto = valorDesconto;
		this.valorTotalDesconto = valorTotalDesconto;
	}
	
	public static ItemCarrinhoDto toDto(ItemCarrinho itemCarrinho) {
		return new ItemCarrinhoDto(itemCarrinho.getProduto().getId(), itemCarrinho.getProduto().getDescricao(), itemCarrinho.getQuantidade(), itemCarrinho.getValorTotal(), itemCarrinho.getValorDesconto(), itemCarrinho.getValorTotalDesconto());
	}
}
