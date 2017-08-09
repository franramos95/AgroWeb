package com.agroWeb.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agroWeb.model.Vacina;
import com.agroWeb.repository.VacinaRepository;
import com.agroWeb.service.exception.ImpossivelExcluirEntidadeException;
import com.agroWeb.service.exception.NomeVacinaJaCadastradaException;


@Service
public class CadastroVacinaService {
	@Autowired
	private VacinaRepository vacinaRepository;

	@Transactional
	public Vacina salva(Vacina vacina) {

		Optional<Vacina> vacinaOptional = vacinaRepository.findByNomeIgnoreCase(vacina.getNome());

		if (vacinaOptional.isPresent() && vacina.isNova()) {
			throw new NomeVacinaJaCadastradaException("Vacina já existente");
		}

		return vacinaRepository.saveAndFlush(vacina);

	}

	@Transactional
	public void excluir(Long id) {
		try {
			vacinaRepository.delete(id);
			vacinaRepository.flush();
		}catch (PersistenceException e){
			throw new ImpossivelExcluirEntidadeException(
					"Impossivel excluir a vacina, a mesma já está sendo utilizada em um animal");
		}
		
	}


}
