package com.agroWeb.repository.helper.dieta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agroWeb.model.Dieta;
import com.agroWeb.repository.filter.DietaFilter;

public interface DietaRepositoryQueries {

	public Page<Dieta> filtrar(DietaFilter filter, Pageable pageable);
	
}
