package com.agroWeb.repository.filter;

import com.agroWeb.model.Especie;
import com.agroWeb.model.SituacaoAnimal;

public class AnimalFilter {
	
	private Long idBrinco;
	
	private String nome;
	
	private Especie especie;
	
	private Long lote;
	
	private SituacaoAnimal situacaoAnimal;

	public Long getIdBrinco() {
		return idBrinco;
	}

	public void setIdBrinco(Long idBrinco) {
		this.idBrinco = idBrinco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public Long getLote() {
		return lote;
	}

	public void setLote(Long lote) {
		this.lote = lote;
	}

	public SituacaoAnimal getSituacaoAnimal() {
		return situacaoAnimal;
	}

	public void setSituacaoAnimal(SituacaoAnimal situacaoAnimal) {
		this.situacaoAnimal = situacaoAnimal;
	}
	
	
	
}
