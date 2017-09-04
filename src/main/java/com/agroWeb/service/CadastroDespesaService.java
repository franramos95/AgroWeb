package com.agroWeb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agroWeb.model.Despesa;
import com.agroWeb.repository.DespesaRepository;
import com.agroWeb.service.exception.NomeDespesaJaCadastradoException;

@Service
public class CadastroDespesaService {

	@Autowired
	private DespesaRepository despesaRepository;

	@Transactional
	public Despesa salva(Despesa despesa) {

		Optional<Despesa> despesaOptional = despesaRepository.findByNomeIgnoreCase(despesa.getNome());

		if (despesaOptional.isPresent() && despesa.isNova()) {
			throw new NomeDespesaJaCadastradoException("Despesa já existente");
		}

		return despesaRepository.saveAndFlush(despesa);

	}

	@Transactional
	public void excluir(Long id) {
		try {
			despesaRepository.delete(id);
			despesaRepository.flush();
		} catch (Exception e) {
			throw e;
		}
	}
}
