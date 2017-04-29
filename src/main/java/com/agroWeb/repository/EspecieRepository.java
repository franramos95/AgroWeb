package com.agroWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agroWeb.model.Especie;
import com.agroWeb.repository.helper.especie.EspecieRepositoryQueries;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Long>, EspecieRepositoryQueries{

	public Optional<Especie> findByNomeIgnoreCase(String nome);
}
