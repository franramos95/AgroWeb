package com.agroWeb.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "vacinas")
public class Vacina implements Serializable {

	private static final long serialVersionUID = 1650745517584727537L;

	@Id
	private Long id;

	@NotBlank(message = "O nome da vacina é obrigatório")
	private String nome;

	@NotNull(message = "O lote da vacina é obrigatório")
	private Long lote;

	@NotNull(message = "A data da vacina é obrigatória")
	private LocalDate data;

	@NotNull(message = "O vencimento da vacina é obrigatória")
	private LocalDate vencimento;

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

	public Long getLote() {
		return lote;
	}

	public void setLote(Long lote) {
		this.lote = lote;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalDate getVencimento() {
		return vencimento;
	}

	public void setVencimento(LocalDate vencimento) {
		this.vencimento = vencimento;
	}

}
