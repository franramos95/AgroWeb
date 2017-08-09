package com.agroWeb.repository.helper.ingredientes;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agroWeb.dto.IngredienteDTO;
import com.agroWeb.model.Ingrediente;
import com.agroWeb.repository.filter.IngredienteFilter;

public interface IngredientesRepositoryQueries {

	public Page<Ingrediente> filtrar(IngredienteFilter filter, Pageable pageable);

	public List<IngredienteDTO> porNome(String nome);
}
