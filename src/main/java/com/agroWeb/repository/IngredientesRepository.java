package com.agroWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agroWeb.model.Ingrediente;
import com.agroWeb.repository.helper.ingredientes.IngredientesRepositoryQueries;

public interface IngredientesRepository extends JpaRepository<Ingrediente, Long>, IngredientesRepositoryQueries {

	public Optional<Ingrediente> findByNomeIgnoreCase(String nome);

}
