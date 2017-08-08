package com.agroWeb.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agroWeb.model.Doenca;
import com.agroWeb.repository.DoencaRepository;
import com.agroWeb.service.exception.ImpossivelExcluirEntidadeException;
import com.agroWeb.service.exception.NomeDoencaJaCadastradaException;

@Service
public class CadastroDoencaService {

	@Autowired
	private DoencaRepository doencaRepository;

	@Transactional
	public Doenca salva(Doenca doenca) {

		Optional<Doenca> doencaOptional = doencaRepository.findByNomeIgnoreCase(doenca.getNome());

		if (doencaOptional.isPresent()) {
			throw new NomeDoencaJaCadastradaException("Doenca já existente");
		}

		return doencaRepository.saveAndFlush(doenca);

	}

	@Transactional
	public void excluir(Long id) {
		try {
			doencaRepository.delete(id);
			doencaRepository.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível excluir a doença, a mesma foi utilizada em um animal");
		}
	}

}
