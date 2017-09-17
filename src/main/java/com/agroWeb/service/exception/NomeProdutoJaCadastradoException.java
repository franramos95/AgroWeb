package com.agroWeb.service.exception;

public class NomeProdutoJaCadastradoException extends RuntimeException {

	private static final long serialVersionUID = -7354852925722271458L;
	
	public NomeProdutoJaCadastradoException (String message){
		super(message);
	}
}
