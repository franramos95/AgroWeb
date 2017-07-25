package com.agroWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agroWeb.model.Animal;
import com.agroWeb.repository.helper.animal.AnimalRepositoryQueries;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>, AnimalRepositoryQueries{

	public Optional<Animal> findByIdBrinco(Long idBrinco);

}
