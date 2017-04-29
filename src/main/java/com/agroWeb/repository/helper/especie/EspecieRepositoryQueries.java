package com.agroWeb.repository.helper.especie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agroWeb.model.Especie;
import com.agroWeb.repository.filter.EspecieFilter;

public interface EspecieRepositoryQueries {
	
	public Page<Especie> filtrar(EspecieFilter filter, Pageable pageable);

}
