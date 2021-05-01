package com.github.com.pedroofilipe.dto;

import com.github.com.pedroofilipe.model.ItemVenda;

public class ItemVendaDto {
	
	private int produtoId;
	private String produtoNome;
	private float quantidade;
	private float valorTotal;
	
	
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
	
	public ItemVendaDto() {
		
	}
	
	public ItemVendaDto(int produtoId, String produtoNome, float quantidade, float valorTotal) {
		this.produtoId = produtoId;
		this.produtoNome = produtoNome;
		this.quantidade = quantidade;
		this.valorTotal = valorTotal;
	}
	
	public static ItemVendaDto toDto(ItemVenda itemVenda) {
		return new ItemVendaDto(itemVenda.getProduto().getId(), itemVenda.getProduto().getDescricao(), itemVenda.getQuantidade(), itemVenda.getValorTotal());
	}
	
	

}
