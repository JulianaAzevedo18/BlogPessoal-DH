package com.blogpessoal.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.blogpessoal.blogpessoal.model.Usuario;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) //indica que a classe UsuarioRepositoryTest é uma classe teste.
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //indica que o ciclo de vida de teste é por classe


public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start(){
		usuarioRepository.deleteAll();
		usuarioRepository.save(new Usuario (0L,"Jose","jojovencio@gmail.com","123","foto1.jpg"));
		usuarioRepository.save(new Usuario (0L,"Lourdes","lourdes.azevedo25-3@gmail.com","123","foto1.jpg"));
		usuarioRepository.save(new Usuario (0L,"Henrique","henriquetk4@hotmail.com","123","foto1.jpg"));
		usuarioRepository.save(new Usuario (0L,"Andressa","Irma","andressa.azevedo91@gmail.com","foto1.jpg"));
	}
	
	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("henriquetk4@hotmail.com");
		assertTrue(usuario.get().getUsuario().equals("henriquetk4@hotmail.com")); //vai receber o resultado
	}
}
