package com.agroWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agroWeb.model.Despesa;
import com.agroWeb.repository.helper.despesa.DespesaRepositoryQueries;


@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long>, DespesaRepositoryQueries {

	public Optional<Despesa> findByNomeIgnoreCase(String nome);

}
