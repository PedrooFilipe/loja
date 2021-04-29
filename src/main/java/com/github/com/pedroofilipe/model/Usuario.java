package com.github.com.pedroofilipe.model;

import javax.persistence.*;

import com.github.com.pedroofilipe.enums.TipoUsuario;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;
    
    public Usuario() {
    	
    }
    
    public Usuario(int id, String nome, String email, String senha, TipoUsuario tipoUsuario) {
    	this.id = id;
    	this.nome = nome;
    	this.email = email;
    	this.senha = senha;
    	this.tipoUsuario = tipoUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
