package com.agroWeb.repository.filter;
import java.time.LocalDate;


public class PesagemFilter {
	
	private LocalDate data;
	private Long peso;
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Long getPeso() {
		return peso;
	}
	public void setPeso(Long peso) {
		this.peso = peso;
	}

}
