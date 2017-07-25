package com.agroWeb.repository.helper.doenca;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agroWeb.model.Doenca;
import com.agroWeb.repository.filter.DoencaFilter;


public interface DoencaRepositoryQueries {
	public Page<Doenca> filtrar(DoencaFilter filter, Pageable pageable);


}
