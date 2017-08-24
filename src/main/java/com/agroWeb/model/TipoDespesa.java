package com.agroWeb.model;

public enum TipoDespesa {

	VACINA("Vacina"),
	INGREDIENTE("Ingrediente"),
	FUNCIONARIO("Funcionario"),
	OUTROS("Outros");
	
	private String tipo;
	
	TipoDespesa(String tipo){
		this.tipo = tipo;
	}
	
	public String getTipo(){
		return tipo;
	}
}
