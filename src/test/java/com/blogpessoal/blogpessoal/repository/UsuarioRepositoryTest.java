package com.blogpessoal.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
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
		usuarioRepository.save(new Usuario (0L,"Jose","jojovencio@gmail.com","12377","foto1.jpg"));
		usuarioRepository.save(new Usuario (0L,"Lourdes","lourdes.azevedo25-3@gmail.com","12355","foto1.jpg"));
		usuarioRepository.save(new Usuario (0L,"Henrique","henriquetk4@hotmail.com","12366","foto1.jpg"));
		usuarioRepository.save(new Usuario (0L,"Andressa","andressa.azevedo91@gmail.com","12366", "foto1.jpg"));
	}
	
	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("henriquetk4@hotmail.com");
		assertTrue(usuario.get().getUsuario().equals("henriquetk4@hotmail.com")); //vai receber o resultado
	}
	
	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Jose");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Jose"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Lourdes"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Henrique"));
	}
}
