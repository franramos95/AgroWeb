package com.agroWeb.repository.helper.pesagem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agroWeb.model.Pesagem;
import com.agroWeb.repository.filter.PesagemFilter;


public interface PesagemRepositoryQueries {

	public Page<Pesagem> filtrar(PesagemFilter filter, Pageable pageable);
}
