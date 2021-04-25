package com.github.com.pedroofilipe.dto;

import java.util.ArrayList;
import java.util.List;

import com.github.com.pedroofilipe.model.Carrinho;
import com.github.com.pedroofilipe.model.ItemCarrinho;
import com.github.com.pedroofilipe.model.Promocao;

public class CarrinhoDto {
	
	private String nomeUsuario;
	private int idUsuario;
	private float valorTotal;
	private List<PromocaoDto> promocoes;
	private List<ItemCarrinhoDto> itensCarrinho;

	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public List<PromocaoDto> getPromocoes() {
		return promocoes;
	}

	public List<ItemCarrinhoDto> getItensCarrinho() {
		return itensCarrinho;
	}

	public CarrinhoDto() {
		
	}
	
	public CarrinhoDto(String nomeUsuario, int idUsuario, float valorTotal, List<Promocao> promocoes, List<ItemCarrinho> itensCarrinho) {
		this.nomeUsuario = nomeUsuario;
		this.idUsuario = idUsuario;
		this.valorTotal = valorTotal;
		this.itensCarrinho = transformarItensCarrinhoEmDto(itensCarrinho);
		this.promocoes = transformarPromocoesEmDto(promocoes);
	}
	
	public static CarrinhoDto toDto(Carrinho carrinho) {
		return new CarrinhoDto(carrinho.getUsuario().getNome(), carrinho.getUsuario().getId(), carrinho.getValorTotal(), carrinho.getPromocoes(), carrinho.getItemCarrinhos());
	}
	
	public List<PromocaoDto> transformarPromocoesEmDto(List<Promocao> promocoes){
		List<PromocaoDto> promocoesDto = new ArrayList<PromocaoDto>();
		if(!promocoes.isEmpty()) {
			for(Promocao promocao : promocoes) {
				promocoesDto.add(PromocaoDto.toDto(promocao));
			}
		}
		
		return promocoesDto;
	}
	
	public List<ItemCarrinhoDto> transformarItensCarrinhoEmDto(List<ItemCarrinho> itensCarrinho){
		List<ItemCarrinhoDto> itensCarrinhoDto = new ArrayList<ItemCarrinhoDto>();
		if(!itensCarrinho.isEmpty()) {
			for(ItemCarrinho itemCarrinho : itensCarrinho) {
				itensCarrinhoDto.add(ItemCarrinhoDto.toDto(itemCarrinho));
			}
		}
		
		return itensCarrinhoDto;
	}
	

}
