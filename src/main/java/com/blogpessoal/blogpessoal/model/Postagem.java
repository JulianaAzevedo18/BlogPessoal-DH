package com.blogpessoal.blogpessoal.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity  //Annotation que indica que é uma entidade no JPA hibernate
@Table(name = "postagem")  // Annotation indica que dentro do BD essa tabela se chamará Postagem

public class Postagem {
	
	@Id //Annotation que indica que será a primary key na tabela postagem
	@GeneratedValue(strategy = GenerationType.IDENTITY) //evita a criação de números iguais
	private long id;
	
	@NotNull //Annotation define que o usuário é obrigado a inserir um conteúdo no título
	@Size(min=5, max=100)
	private String titulo;
	
	@NotNull
	@Size(min=5, max=500)
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new java.sql.Date(System.currentTimeMillis());
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
