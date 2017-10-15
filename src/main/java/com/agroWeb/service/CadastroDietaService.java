package com.agroWeb.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agroWeb.model.Dieta;
import com.agroWeb.repository.DietaRepository;
import com.agroWeb.service.event.dieta.DietaEvent;
import com.agroWeb.service.exception.ImpossivelExcluirEntidadeException;
import com.agroWeb.service.exception.NomeDietaJaCadastradoException;

@Service
public class CadastroDietaService {

	@Autowired
	public DietaRepository dietaRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Transactional
	public Dieta salvar(Dieta dieta) {

		Optional<Dieta> dietaOption = dietaRepository.findByNome(dieta.getNome());

		if (dietaOption.isPresent() && dieta.isNovo()) {
			throw new NomeDietaJaCadastradoException("Dieta já cadastrada");
		}

		// BigDecimal valorTotalDieta = dieta.getItens().stream()
		// .map(ItemDieta::getValorTotal)
		// .reduce(BigDecimal::add)
		// .get();

		publisher.publishEvent(new DietaEvent(dieta));
		return dietaRepository.save(dieta);

		
	}

	@Transactional
	public void excluir(Long id) {
		try {
			dietaRepository.delete(id);
			dietaRepository.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível excluir a dieta");
		}
	}

}
