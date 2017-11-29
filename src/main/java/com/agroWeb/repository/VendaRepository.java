package com.agroWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agroWeb.model.Venda;
import com.agroWeb.repository.helper.venda.VendaRepositoryQueries;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long>, VendaRepositoryQueries {

	public Optional<Venda> findByNomeIgnoreCase(String nome);
}
