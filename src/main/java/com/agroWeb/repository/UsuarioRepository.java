package com.agroWeb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agroWeb.model.Usuario;
import com.agroWeb.repository.helper.usuario.UsuarioRepositoryQueries;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQueries{

	public Optional<Usuario> findByEmailIgnoreCase(String email);
	
	public List<Usuario> findByCodigoIn(Long[] codigos);
}

