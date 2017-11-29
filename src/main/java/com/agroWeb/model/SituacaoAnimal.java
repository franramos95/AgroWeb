package com.agroWeb.model;

public enum SituacaoAnimal {

	ABATIDO("Abatido"), VENDIDO("Vendido"), VIVO("Vivo"), MORTO("Morto");

	private String descricao;

	SituacaoAnimal(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
