package com.agroWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agroWeb.model.Vacina;
import com.agroWeb.repository.helper.vacina.VacinaRepositoryQueries;

@Repository
public interface VacinaRepository extends JpaRepository<Vacina, Long>, VacinaRepositoryQueries {

	public Optional<Vacina> findByNomeIgnoreCase(String nome);

}