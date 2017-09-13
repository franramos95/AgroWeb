package com.agroWeb.repository.filter;

import java.time.LocalDate;

import com.agroWeb.model.Comprador;


public class VendaFilter {


	private Long valorTotal;
	
	private LocalDate dataVenda;

	private Comprador  comprador;

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

	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}


}
