package br.com.cotinformatica.exceptions;

public class AcessoNegadoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public AcessoNegadoException() {
		super("Acesso negado. Usuário inválido");
	}

}
