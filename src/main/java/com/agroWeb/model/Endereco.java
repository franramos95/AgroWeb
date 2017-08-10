package com.agroWeb.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Embeddable
public class Endereco implements Serializable {

	private static final long serialVersionUID = 7419068424459081310L;

	private String logradouro;
	private Long numero;
	private String bairro;
	private String cep;

	@NotNull(message = "A cidade é obrigatória")
	@ManyToOne
	@JoinColumn(name = "codigo_cidade")
	private Cidade cidade;

	@Transient
	private Estado estado;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		if (this.cidade != null) {
			return this.cidade.getEstado();
		}

		return null;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getNomeCidadeSiglaEstado() {
		if (this.cidade != null) {
			return this.cidade.getNome() + "/" + this.cidade.getEstado().getSigla();
		}
		return null;
	}

}
