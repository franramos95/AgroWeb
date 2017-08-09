package com.agroWeb.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agroWeb.model.Usuario;
import com.agroWeb.repository.filter.UsuarioFilter;

public interface UsuarioRepositoryQueries {
	
	public Optional<Usuario> porEmailEAtivo(String email);

	public List<String> permissoes(Usuario usuario);

	public Page<Usuario> filtrar(UsuarioFilter filtro, Pageable pageable);

	public Usuario buscarComGrupos(Long codigo);

}