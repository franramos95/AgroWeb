package com.agroWeb.repository.helper.animal;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agroWeb.dto.MortalidadeMes;
import com.agroWeb.model.Animal;
import com.agroWeb.repository.filter.AnimalFilter;

public interface AnimalRepositoryQueries {

	public Page<Animal> filtrar(AnimalFilter filter, Pageable pageable);

	public Long totalDeAnimais();

	List<MortalidadeMes> totalPorMes();
}
