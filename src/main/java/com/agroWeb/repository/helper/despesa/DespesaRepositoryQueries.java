package com.agroWeb.repository.helper.despesa;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agroWeb.model.Despesa;
import com.agroWeb.repository.filter.DespesaFilter;

public interface DespesaRepositoryQueries {

	public Page<Despesa> filtrar(DespesaFilter filter, Pageable pageable);
	
	public BigDecimal totalDeDespesa();
	
}
