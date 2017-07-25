package com.agroWeb.repository.helper.venda;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agroWeb.model.Venda;
import com.agroWeb.repository.filter.VendaFilter;

public interface VendaRepositoryQueries {

	public Page<Venda> filtrar(VendaFilter filter, Pageable pageable);
}
