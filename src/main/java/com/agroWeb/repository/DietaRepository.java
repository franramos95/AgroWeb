package com.agroWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agroWeb.model.Dieta;
import com.agroWeb.repository.helper.dieta.DietaRepositoryQueries;

@Repository
public interface DietaRepository extends JpaRepository<Dieta, Long>, DietaRepositoryQueries {


	public Optional<Dieta> findByNome(String nome);


}
