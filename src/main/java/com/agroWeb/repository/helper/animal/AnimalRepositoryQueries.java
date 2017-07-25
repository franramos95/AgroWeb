package com.agroWeb.repository.helper.animal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agroWeb.model.Animal;
import com.agroWeb.repository.filter.AnimalFilter;

public interface AnimalRepositoryQueries {
	
	public Page<Animal> filtrar(AnimalFilter filter, Pageable pageable);
	

}
