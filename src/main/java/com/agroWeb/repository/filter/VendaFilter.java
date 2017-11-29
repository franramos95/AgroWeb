package com.agroWeb.repository.filter;

import java.time.LocalDate;

import com.agroWeb.model.Comprador;
import com.agroWeb.model.StatusVenda;

public class VendaFilter {

	private String nome;

	private Long valor;

	private LocalDate desde;
	
	private LocalDate ate;

	private Comprador comprador;

	private StatusVenda status;

	
	public LocalDate getAte() {
		return ate;
	}

	public void setAte(LocalDate ate) {
		this.ate = ate;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public LocalDate getDesde() {
		return desde;
	}

	public void setDesde(LocalDate desde) {
		this.desde = desde;
	}

	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}

	public StatusVenda getStatus() {
		return status;
	}

	public void setStatus(StatusVenda status) {
		this.status = status;
	}

}