package com.blogpessoal.blogpessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.blogpessoal.blogpessoal.model.Usuario;
import com.blogpessoal.blogpessoal.repository.UsuarioRepository;
import com.blogpessoal.blogpessoal.service.UsuarioService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // indica que os testes serão executados na ordem 

public class UsuarioControllerTest {
	
	@Autowired //injeção de dependencia
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository; //vai fazer a limpeza do bd de teste
	
	@BeforeAll
	void start(){
		usuarioRepository.deleteAll();
	}
	
	@Test
	@Order(1)
	@DisplayName("Cadastrar um usuário:")
	public void deveCriarUsuario() {
		HttpEntity <Usuario> requisicao = new HttpEntity <Usuario> (new Usuario(0L,"Henrique","henriquetk4@hotmail.com","123","foto1.jpg"));
		ResponseEntity <Usuario> resposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao, Usuario.class);
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode()); //cheeca se a resposta é a resposta esperada
		assertEquals(requisicao.getBody().getNome(), resposta.getBody().getNome());
		assertEquals(requisicao.getBody().getUsuario(), resposta.getBody().getUsuario());
		assertEquals(requisicao.getBody().getFoto(), resposta.getBody().getFoto());
	}
	
	@Test
	@Order(2)
	@DisplayName("Não deve permitir duplicação de usuário")
	public void naoDeveDuplicarUsuario() {
		usuarioService.CadastrarUsuario(new Usuario(0L,"Andressa","andressa.azevedo@91@gmail.com","1234","foto2.jpg"));
	HttpEntity <Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L,"Andressa","andressa.azevedo91@gmail.com","1234","foto2.jpg"));
	ResponseEntity <Usuario> resposta = testRestTemplate.exchange("/usuario/cadastrar", HttpMethod.POST, requisicao, Usuario.class);
	assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode()); //testar o erro do usuario duplicado
	}
	
	@Test
	@Order(3)
	@DisplayName("Alterar um usuário")
	public void deveAlterarUmUsuario() {
		
		Optional <Usuario> usuarioCreate = usuarioService.CadastrarUsuario(0L,"Jose","jojovencio@gmail.com","1234","foto3.jpg");
		Usuario usuarioUpdate = new Usuario(usuarioCreate.get().getId(), 
				"Jose Azevedo","jojovencio@gmail.com","1234","foto3.jpg");
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(usuarioUpdate);
		ResponseEntity<Usuario> resposta = testRestTemplate.withBasicAuth("root", "root").exchange("/usuarios/cadastrar",HttpMethod.PUT, requisicao, Usuario.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(usuarioUpdate.getNome(), resposta.getBody().getNome());
		assertEquals(usuarioUpdate.getUsuario(), resposta.getBody().getUsuario());
		assertEquals(usuarioUpdate.getFoto(), resposta.getBody().getFoto());
	}
	
}
