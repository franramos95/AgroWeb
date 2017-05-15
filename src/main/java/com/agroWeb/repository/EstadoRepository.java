package com.agroWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agroWeb.model.Estado;

@Repository
public interface EstadoRepository  extends JpaRepository<Estado, Long>{

}
