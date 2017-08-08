package com.agroWeb.repository;


import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agroWeb.model.Ingrediente;
import com.agroWeb.repository.helper.ingredientes.IngredientesRepositoryQueries;

@Repository
public interface IngredientesRepository extends JpaRepository<Ingrediente, Long>, IngredientesRepositoryQueries {

	public Optional<Ingrediente> findByNomeIgnoreCase(String nome);

}
