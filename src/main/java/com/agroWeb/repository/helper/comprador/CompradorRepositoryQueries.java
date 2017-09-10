package com.agroWeb.repository.helper.comprador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agroWeb.model.Comprador;
import com.agroWeb.repository.filter.CompradorFilter;
public interface CompradorRepositoryQueries {
	
	public Page<Comprador> filtrar(CompradorFilter filter, Pageable pageable);

	public Comprador buscarComCidadeEstado(Long id);
	
	public Long totalDeCompradores();
}
