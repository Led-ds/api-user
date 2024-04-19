package br.com.cotinformatica.interfaces;

import br.com.cotinformatica.dtos.UsuarioAuthRequest;
import br.com.cotinformatica.dtos.UsuarioAuthResponse;
import br.com.cotinformatica.dtos.UsuarioRequest;
import br.com.cotinformatica.dtos.UsuarioResponse;
import br.com.cotinformatica.exceptions.EmailJaCadastradoException;

public interface UsuarioService {
	/**
	 * Método para criar usuário
	 * */
	UsuarioResponse criarUsuario(final UsuarioRequest prUsuarioReq);
	
	/**
	 * Método para autenticar usuário
	 * */
	UsuarioAuthResponse autenticarUsuario(final UsuarioAuthRequest prAutUsuarioAuthReq);

}
