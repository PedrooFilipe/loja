package com.github.com.pedroofilipe.dto;

import java.util.ArrayList;
import java.util.List;

import com.github.com.pedroofilipe.model.ItemVenda;
import com.github.com.pedroofilipe.model.Venda;

public class VendaDto {
	
	private float valorTotal;
	private String nomeUsuario;
	private float valorTotalDesconto;
	private float valorDesconto;
	private String tipoPagamento;
	private List<ItemVendaDto> itensVenda;
	
	public VendaDto(float valorTotal, String nomeUsuario, float valorTotalDesconto, float valorDesconto, String tipoPagamento, List<ItemVenda> itensVenda) {
		this.valorTotal = valorTotal;
		this.nomeUsuario = nomeUsuario;
		this.valorTotalDesconto = valorTotalDesconto;
		this.valorDesconto = valorDesconto;
		this.tipoPagamento = tipoPagamento;
		this.itensVenda = transformarItemVendaEmDto(itensVenda);
	}
	
	public static VendaDto toDto(Venda venda) {
		return new VendaDto(venda.getValorTotal(), venda.getUsuario().getNome(), 
				venda.getValorTotalDesconto(), venda.getValorDesconto(), venda.getTipoPagamento().toString(), venda.getItemVendas());
	}
	
	public List<ItemVendaDto> transformarItemVendaEmDto(List<ItemVenda> itensVenda){
		List<ItemVendaDto> itensDto = new ArrayList<ItemVendaDto>();
		if(!itensVenda.isEmpty()) {
			for(ItemVenda item : itensVenda) {
				itensDto.add(ItemVendaDto.toDto(item));
			}
		}
		return itensDto;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
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

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public List<ItemVendaDto> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVendaDto> itensVenda) {
		this.itensVenda = itensVenda;
	}
}
