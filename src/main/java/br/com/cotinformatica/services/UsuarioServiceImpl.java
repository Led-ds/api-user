package br.com.cotinformatica.services;

import java.util.Date;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotinformatica.components.CryptoComponent;
import br.com.cotinformatica.components.TokenComponent;
import br.com.cotinformatica.dtos.UsuarioAuthRequest;
import br.com.cotinformatica.dtos.UsuarioAuthResponse;
import br.com.cotinformatica.dtos.UsuarioRequest;
import br.com.cotinformatica.dtos.UsuarioResponse;
import br.com.cotinformatica.entities.Usuario;
import br.com.cotinformatica.exceptions.AcessoNegadoException;
import br.com.cotinformatica.exceptions.EmailJaCadastradoException;
import br.com.cotinformatica.interfaces.UsuarioService;
import br.com.cotinformatica.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CryptoComponent cryptoComponent;
	
	@Autowired
	private TokenComponent tokenComponent;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UsuarioResponse criarUsuario(final UsuarioRequest prUsuarioReq) {

		if (usuarioRepository.findByEmail(prUsuarioReq.getEmail()) != null) {
			throw new EmailJaCadastradoException();
		}

		Usuario usuario = modelMapper.map(prUsuarioReq, Usuario.class);
		usuario.setId(UUID.randomUUID());
		usuario.setSenha(cryptoComponent.sha256Encrypt(prUsuarioReq.getSenha()));

		//salvando...
		usuarioRepository.save(usuario);
		
		UsuarioResponse resp = modelMapper.map(usuario, UsuarioResponse.class);
		resp.setDataHoraCadastro(new Date());

		return resp;
	}

	@Override
	public UsuarioAuthResponse autenticarUsuario(final UsuarioAuthRequest prAutUsuarioAuthReq) {
		
		Usuario usuario = usuarioRepository.findByEmailAndSenha(prAutUsuarioAuthReq.getEmail(), cryptoComponent.sha256Encrypt(prAutUsuarioAuthReq.getSenha()));
		
		if(usuario == null) {
			throw new AcessoNegadoException();
		}
		
		UsuarioAuthResponse response = modelMapper.map(usuario, UsuarioAuthResponse.class);
		
		response.setToken(tokenComponent.generateToken(usuario.getId()));	
		response.setDataHoraAcesso(new Date());
		
		return response;
	}

}
