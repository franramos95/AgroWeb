package com.agroWeb.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agroWeb.model.Venda;
import com.agroWeb.repository.VendaRepository;
import com.agroWeb.service.event.venda.VendaEvent;
import com.agroWeb.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroVendaService {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Transactional
	public void salva(Venda venda) {

		publisher.publishEvent(new VendaEvent(venda));
		vendaRepository.saveAndFlush(venda);
	}

	@Transactional
	public void excluir(Long codigo) {
		try {
			vendaRepository.delete(codigo);
			vendaRepository.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossivel excluir a venda");
		}
	}
}
