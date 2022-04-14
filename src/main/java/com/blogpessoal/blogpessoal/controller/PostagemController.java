package com.blogpessoal.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogpessoal.blogpessoal.model.PostagemModel;
import com.blogpessoal.blogpessoal.repository.PostagemRepository;

@RestController //informa para o spring que essa classe se trata de um controlador
@RequestMapping("/postagens") //indica em qual URI a classe será acessada
@CrossOrigin ("*") //indica que aceita requisições de qualquer origem

public class PostagemController {
	
	@Autowired //fornece controle sobre onde e como a ligação entre os beans deve ser realizada.
	private PostagemRepository repository;
	
	
	//retona lista do tipo postagem e o nome do método será GETALL sem parametros, pq vai retornar 
	public ResponseEntity<List<PostagemModel>>GetAll() {
		
		//receberá um objeto do tipo ReponseEntity, passando um "ok" para indicar uma resposta para validar 
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping ("/{id}") //indica um método de get
	public ResponseEntity <PostagemModel> GetById(@PathVariable long id){
	return repository.findById(id)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping ("/titulo/{titulo}")
	
	public ResponseEntity<List<PostagemModel>> GetByTitulo (@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<PostagemModel> post (@RequestBody PostagemModel postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<PostagemModel> put (@RequestBody PostagemModel postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	@DeleteMapping ("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	/*Uma expressão Lambda permite definir uma interface funcional 
	 * (novamente, um método abstrato) que o compilador identifica pela estrutura. 
	 * O compilador pode determinar a interface funcional representada a partir 
	 * de sua posição. O tipo de uma expressão lambda é o da interface funcional 
	 * associada.
	 */
	
}

