package br.com.cotinformatica.dtos;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class UsuarioResponse {

	private UUID id;
	
	private String nome;
	
	private String email;
	
	private Date dataHoraCadastro;


}
