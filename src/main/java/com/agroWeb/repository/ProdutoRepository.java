package com.agroWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agroWeb.model.Produto;
import com.agroWeb.repository.helper.produto.ProdutoRepositoryQueries;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryQueries{
	
	public Optional<Produto> findByNomeIgnoreCase(String nome);
	
}
