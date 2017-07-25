package com.agroWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agroWeb.model.Dieta;

@Repository
public interface DietaRepository extends JpaRepository<Dieta, Long> {

}
