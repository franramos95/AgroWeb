package com.agroWeb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agroWeb.model.Doenca;
import com.agroWeb.repository.DoencaRepository;
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

}
