package com.agroWeb.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.agroWeb.security.exception.EmailCadastradoException;

@RestControllerAdvice
public class ControllerAdviceExceptionHandler {
	
	@ExceptionHandler(EmailCadastradoException.class)
	public ResponseEntity<String> handleNomeEstiloJaCadastradoException(EmailCadastradoException e){
		
		return ResponseEntity.badRequest().body(e.getMessage());
		
	}
}
