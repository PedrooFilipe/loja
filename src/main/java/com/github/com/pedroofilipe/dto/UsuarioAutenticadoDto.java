package com.github.com.pedroofilipe.dto;

import com.github.com.pedroofilipe.model.Usuario;

public class UsuarioAutenticadoDto {
	
	private String nome;
	private String email;
	private String token;
	
	public UsuarioAutenticadoDto() {
		
	}
	
	public UsuarioAutenticadoDto(String nome, String email, String token) {
		this.nome = nome;
		this.email = email;
		this.token = token;
	}

	
	public UsuarioAutenticadoDto toDto(Usuario usuario){
		return new UsuarioAutenticadoDto(usuario.getNome(), usuario.getEmail(), usuario.getToken());
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
