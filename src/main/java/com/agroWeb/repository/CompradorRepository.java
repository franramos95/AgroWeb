package com.agroWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agroWeb.model.Comprador;
import com.agroWeb.repository.helper.comprador.CompradorRepositoryQueries;

public interface CompradorRepository extends JpaRepository<Comprador, Long> , CompradorRepositoryQueries{
	
	public Optional<Comprador> findByNomeIgnoreCase(String nome);
}
