package com.blogpessoal.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.blogpessoal.blogpessoal.model.PostagemModel;

@Repository //indica para o string que se trata de uma classe repository (consulta para o BD)
public interface PostagemRepository extends JpaRepository <PostagemModel, Long>{
	
	public List <PostagemModel> findAllByTituloContainingIgnoreCase(String titulo); //uma lista de postagem, "findAll" = fará a busca de todas as postagem pelo título, "IgnoreCase" vai ignorar as direfeças do maisculo e minusculo

	
}
