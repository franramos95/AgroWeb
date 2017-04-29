package com.agroWeb.model;

public enum SituacaoAnimal {

	ABATIDO("Abatido"), MORTO("Morto"), VENDIDO("Vendido"), VIVO("Vivo");

	private String descricao;

	SituacaoAnimal(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
