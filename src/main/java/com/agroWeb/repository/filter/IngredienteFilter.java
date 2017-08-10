package com.agroWeb.repository.filter;

import java.math.BigDecimal;

public class IngredienteFilter {

	private String nome;
	private BigDecimal valor;
	private Long quantidade = null;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

}
