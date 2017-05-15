package com.agroWeb.repository.helper.cidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agroWeb.model.Cidade;
import com.agroWeb.repository.filter.CidadeFilter;

public interface CidadeRepositoryQueries {
	public Page<Cidade> filtrar(CidadeFilter filter, Pageable pageable);

}
