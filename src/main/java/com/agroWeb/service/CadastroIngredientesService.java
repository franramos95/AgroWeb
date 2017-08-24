package com.agroWeb.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agroWeb.model.Despesa;
import com.agroWeb.model.Ingrediente;
import com.agroWeb.model.TipoDespesa;
import com.agroWeb.repository.DespesaRepository;
import com.agroWeb.repository.IngredientesRepository;
import com.agroWeb.service.exception.ImpossivelExcluirEntidadeException;
import com.agroWeb.service.exception.NomeIngredienteJaCadastradaException;

@Service
public class CadastroIngredientesService {

	@Autowired
	private IngredientesRepository ingredientesRepository;

	@Autowired
	private CadastroDespesaService cadastroDespesaService;

	@Autowired
	private DespesaRepository despesaRepository;

	@Transactional
	public Ingrediente salva(Ingrediente ingrediente) {

		Optional<Ingrediente> ingredienteOptional = ingredientesRepository.findByNomeIgnoreCase(ingrediente.getNome());

		if (ingredienteOptional.isPresent() && ingrediente.isNova()) {
			throw new NomeIngredienteJaCadastradaException("Ingrediente já existente");
		}

		if (ingrediente.isNova()) {
			try {
				Despesa despesa = new Despesa();
				despesa.setNome(ingrediente.getNome());
				despesa.setTipoDespesa(TipoDespesa.INGREDIENTE);
				despesa.setValor(ingrediente.getValor());
				despesa.setData(LocalDate.now());
				ingrediente.setDespesa(despesa);
				cadastroDespesaService.salva(despesa);
				return ingredientesRepository.saveAndFlush(ingrediente);
			} catch (Exception e) {
				throw new NomeIngredienteJaCadastradaException("Não foi possivel cadastrar o ingrediente.");
			}
		} else {
			try {
				Despesa despesa = despesaRepository.findOne(ingrediente.getDespesa().getId());
				despesa.setNome(ingrediente.getNome());
				despesa.setTipoDespesa(TipoDespesa.INGREDIENTE);
				despesa.setValor(ingrediente.getValor());
				despesa.setData(LocalDate.now());
				cadastroDespesaService.salva(despesa);
				return ingredientesRepository.saveAndFlush(ingrediente);
			} catch (Exception e) {
				throw e;
			}

		}
	}



	@Transactional
	public void excluir(Long id) {
		try {
			Ingrediente ingrediente = ingredientesRepository.findOne(id);
			cadastroDespesaService.excluir(ingrediente.getDespesa().getId());
			ingredientesRepository.delete(id);
			ingredientesRepository.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível excluir o ingrediente");
		}
	}
}
