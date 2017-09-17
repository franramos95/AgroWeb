package com.agroWeb.repository.helper.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agroWeb.model.Produto;
import com.agroWeb.repository.filter.ProdutoFilter;

public interface ProdutoRepositoryQueries {

	public Page<Produto> filtrar(ProdutoFilter filter, Pageable pageable);
}
