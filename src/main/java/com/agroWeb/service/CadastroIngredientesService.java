package com.agroWeb.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agroWeb.model.Ingrediente;
import com.agroWeb.repository.IngredientesRepository;
import com.agroWeb.service.exception.ImpossivelExcluirEntidadeException;
import com.agroWeb.service.exception.NomeIngredienteJaCadastradaException;

@Service
public class CadastroIngredientesService {

	@Autowired
	private IngredientesRepository ingredientesRepository;

	@Transactional
	public Ingrediente salva(Ingrediente ingrediente) {

		Optional<Ingrediente> ingredienteOptional = ingredientesRepository.findByNomeIgnoreCase(ingrediente.getNome());

		if (ingredienteOptional.isPresent()) {
			throw new NomeIngredienteJaCadastradaException("Ingrediente já existente");
		}

		return ingredientesRepository.saveAndFlush(ingrediente);

	}

	@Transactional
	public void excluir(Long id) {
		try {
			ingredientesRepository.delete(id);
			ingredientesRepository.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException(
					"Impossível excluir a doença, a mesma foi utilizada em um animal");
		}
	}
}
