package com.github.com.pedroofilipe.dto;

import com.github.com.pedroofilipe.model.Usuario;

public class UsuarioDto {
	
	private String nome;
	private String email;
	
	public UsuarioDto() {
		
	}
	
	public UsuarioDto(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}

	
	public static UsuarioDto toDto(Usuario usuario){
		return new UsuarioDto(usuario.getNome(), usuario.getEmail());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
