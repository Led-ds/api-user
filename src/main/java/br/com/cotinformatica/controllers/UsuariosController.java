package br.com.cotinformatica.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotinformatica.dtos.UsuarioAuthRequest;
import br.com.cotinformatica.dtos.UsuarioAuthResponse;
import br.com.cotinformatica.dtos.UsuarioRequest;
import br.com.cotinformatica.dtos.UsuarioResponse;
import br.com.cotinformatica.interfaces.UsuarioService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("criar")
	public ResponseEntity<UsuarioResponse> criar(@RequestBody @Valid UsuarioRequest request) {
		
		UsuarioResponse response = usuarioService.criarUsuario(request);
		
		return ResponseEntity.status(201).body(response);

	}
	
	@PostMapping("autenticar")
	public ResponseEntity<UsuarioAuthResponse> autenticar(@RequestBody @Valid UsuarioAuthRequest request) {
		
		return ResponseEntity.status(200).body(usuarioService.autenticarUsuario(request));
		
	}
	
}
