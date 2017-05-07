package com.agroWeb.service.exception;

public class NomeCompradorJaCadastradoException extends RuntimeException {

	
	private static final long serialVersionUID = -5169540629735535969L;

	public NomeCompradorJaCadastradoException (String message){
		super(message);
	}
}
