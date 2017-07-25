package com.agroWeb.repository.filter;

import java.time.LocalDate;


public class VendaFilter {


	private Long arroba;
	
	private Long valorArroba;

	private Long valorTotal;
	
	private LocalDate dataVenda;

	private String  nomeComprador;

	public Long getArroba() {
		return arroba;
	}

	public void setArroba(Long arroba) {
		this.arroba = arroba;
	}

	public Long getValorArroba() {
		return valorArroba;
	}

	public void setValorArroba(Long valorArroba) {
		this.valorArroba = valorArroba;
	}

	public Long getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Long valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getNomeComprador() {
		return nomeComprador;
	}

	public void setNomeComprador(String nomeComprador) {
		this.nomeComprador = nomeComprador;
	}


	
}
