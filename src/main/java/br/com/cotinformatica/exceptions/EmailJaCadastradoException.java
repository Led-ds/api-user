package br.com.cotinformatica.exceptions;


/*
 * 
 * Classe de exceção customizada
 * 
 * */
public class EmailJaCadastradoException extends RuntimeException{


	private static final long serialVersionUID = 1L;

	public EmailJaCadastradoException() {
		super("Email informado já está cadastrado. Tente outro.");
	}
	
	
}
