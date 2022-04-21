package com.blogpessoal.blogpessoal.model;

//uma classe interna do Spring, não terá annotation - entrega uma responsta uquando o usuário entrar no sistema
public class UsuarioLogin {
	
	private String nome;
	private String usuario;
	private String senha;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
