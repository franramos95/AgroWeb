package com.agroWeb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agroWeb.model.Vacina;
import com.agroWeb.repository.VacinaRepository;
import com.agroWeb.service.exception.NomeVacinaJaCadastradaException;


@Service
public class CadastroVacinaService {
	@Autowired
	private VacinaRepository vacinaRepository;

	@Transactional
	public Vacina salva(Vacina vacina) {

		Optional<Vacina> vacinaOptional = vacinaRepository.findByNomeIgnoreCase(vacina.getNome());

		if (vacinaOptional.isPresent()) {
			throw new NomeVacinaJaCadastradaException("Vacina já existente");
		}

		return vacinaRepository.saveAndFlush(vacina);

	}


}
