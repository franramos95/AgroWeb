package com.agroWeb.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="comprador")
public class Comprador implements Serializable {
	
	
	private static final long serialVersionUID = 4849238570543986556L;

	@Id
	private Long id;
	
	@NotBlank(message = "O nome do comprador é obrigatório")
	private String nome;
	
	@NotNull(message = "A inscrição do comprador é obrigatória")
	private Long inscricao;
	
	@NotBlank(message = "O lodogradouro do comprador é obrigatório")
	private String logradouro;
	
	@NotNull(message = "O numero do endereço do comprador é obttigatório")
	private Long numero;
	
	@NotBlank(message = "O bairro do comprador é obrigatório")
	private String bairro;
	
	@NotBlank(message = "A cidade do comprador é obrigatório")
	private String cidade;
	
	@NotBlank(message = "O Estado do comprador é obrigatório")
	private String estado;
	
	@NotBlank(message = "O País do comprador é obrigatório")
	private String pais;
	
	@Max(value =  7)
	private Long cep;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getInscricao() {
		return inscricao;
	}

	public void setInscricao(Long inscricao) {
		this.inscricao = inscricao;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long num) {
		this.numero = num;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Long getCep() {
		return cep;
	}

	public void setCep(Long cep) {
		this.cep = cep;
	}


}
