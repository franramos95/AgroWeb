package com.agroWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agroWeb.model.Pesagem;
import com.agroWeb.repository.helper.pesagem.PesagemRepositoryQueries;

public interface PesagemRepository extends JpaRepository<Pesagem, Long>, PesagemRepositoryQueries {
	
	
}
