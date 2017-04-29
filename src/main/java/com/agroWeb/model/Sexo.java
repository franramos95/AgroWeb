package com.agroWeb.model;

public enum Sexo {
	
	MACHO("Macho"),
	FEMEA("Femea");
	
	private String descricao;
	
	Sexo(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}

}
