package com.agroWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agroWeb.repository.helper.doenca.DoencaRepositoryQueries;

import com.agroWeb.model.Doenca;

@Repository
public interface DoencaRepository extends JpaRepository<Doenca, Long>, DoencaRepositoryQueries {

	public Optional<Doenca> findByNomeIgnoreCase(String nome);

}