package com.agroWeb.repository.filter;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.agroWeb.model.TipoDespesa;

public class DespesaFilter {

	private String nome;
	private TipoDespesa tipoDespesa;
	private BigDecimal valor;
	private LocalDate desde;
	private LocalDate ate;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoDespesa getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(TipoDespesa tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getDesde() {
		return desde;
	}

	public void setDesde(LocalDate desde) {
		this.desde = desde;
	}

	public LocalDate getAte() {
		return ate;
	}

	public void setAte(LocalDate ate) {
		this.ate = ate;
	}

}
