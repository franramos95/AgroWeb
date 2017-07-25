package com.agroWeb.repository.helper.vacina;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agroWeb.model.Vacina;
import com.agroWeb.repository.filter.VacinaFilter;

public interface VacinaRepositoryQueries {
	
	public Page<Vacina> filtrar(VacinaFilter filter, Pageable pageable);

}
