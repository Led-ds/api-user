package br.com.cotinformatica;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Locale;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import br.com.cotinformatica.dtos.UsuarioRequest;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuariosTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private static String nome;
	private static String email;
	private static String senha;
	
	@SuppressWarnings("static-access")
	@Test
	@Order(1)
	public void quandoCriarUsuario_entaoRetornarStatus201() throws Exception {
		Faker faker = new Faker(new Locale("pt-BR"));
		UsuarioRequest dto = new UsuarioRequest();
		
		dto.setNome(faker.name().fullName());
		dto.setEmail(faker.internet().emailAddress());
		dto.setSenha("Teste@123");
		
		
		
		mockMvc.perform(post("/api/usuarios/criar")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated());
		
		this.nome = dto.getNome();
		this.email = dto.getEmail();
		this.senha = dto.getSenha();
		
	}
	
	@Test
	@Order(2)
	public void quandoCriarUsuarioComEmailJaCadastrado_entaoRetornarStatus422() throws Exception {
		UsuarioRequest dto = new UsuarioRequest();

		dto.setNome(nome);
		dto.setEmail(email);
		dto.setSenha(senha);

		mockMvc.perform(post("/api/usuarios/criar").contentType("application/json")
				.content(objectMapper.writeValueAsString(dto))).andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	@Order(3)
	public void quandoAutenticarUsuario_entaoRetornarStatus200() throws Exception {
		UsuarioRequest dto = new UsuarioRequest();
		dto.setEmail(email);
		dto.setSenha(senha);
		
		mockMvc.perform(post("/api/usuarios/autenticar").contentType("application/json")
														.content(objectMapper.writeValueAsString(dto)))
														.andExpect(status().isOk());
	}
	
	
	@Test
	@Order(4)
	public void quandoAutenticarUsuarioInvalido_entaoRetornarStatus401() throws Exception {
		UsuarioRequest dto = new UsuarioRequest();
		dto.setEmail(email);
		dto.setSenha("@Lasa2030");
		
		mockMvc.perform(post("/api/usuarios/autenticar").contentType("application/json")
				.content(objectMapper.writeValueAsString(dto))).andExpect(status().isUnauthorized());
	}

}



