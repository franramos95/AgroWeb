package com.agroWeb.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agroWeb.model.Venda;
import com.agroWeb.repository.VendaRepository;
import com.agroWeb.service.exception.ImpossivelExcluirEntidadeException;
import com.agroWeb.service.exception.NomeVendaJaCadastradoException;

@Service
public class CadastroVendaService {

	@Autowired
	private VendaRepository vendaRepository;


	@Transactional
	public Venda salva(Venda venda) {
		Optional<Venda> vendaOptional = vendaRepository.findByNomeIgnoreCase(venda.getNome());

		if(vendaOptional.isPresent() && venda.isNova()){
			throw new NomeVendaJaCadastradoException("Venda já existente");
		}
		
		return vendaRepository.save(venda);
		
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
