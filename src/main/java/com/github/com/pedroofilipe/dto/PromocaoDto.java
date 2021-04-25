package com.github.com.pedroofilipe.dto;

import com.github.com.pedroofilipe.enums.TipoDesconto;
import com.github.com.pedroofilipe.enums.TipoPromocao;
import com.github.com.pedroofilipe.model.Promocao;

public class PromocaoDto {
	
	private float valorDesconto;
	private String descricao;
	private TipoDesconto tipoDesconto;
	private TipoPromocao tipoPromocao;
	
	public float getValorDesconto() {
		return valorDesconto;
	}
	public String getDescricao() {
		return descricao;
	}
	public TipoDesconto getTipoDesconto() {
		return tipoDesconto;
	}
	public TipoPromocao getTipoPromocao() {
		return tipoPromocao;
	}
	
	public PromocaoDto() {
		
	}
	
	public PromocaoDto(float valorDesconto, String descricao, TipoDesconto tipoDesconto, TipoPromocao tipoPromocao) {
		this.valorDesconto = valorDesconto;
		this.descricao = descricao;
		this.tipoDesconto = tipoDesconto;
		this.tipoPromocao = tipoPromocao;
	}
	
	public static PromocaoDto toDto(Promocao promocao) {
		return new PromocaoDto(promocao.getValorDesconto(), promocao.getDescricao(), promocao.getTipoDesconto(), promocao.getTipoPromocao());
	}
}
