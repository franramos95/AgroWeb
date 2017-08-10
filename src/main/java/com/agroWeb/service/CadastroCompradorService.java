package com.agroWeb.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agroWeb.model.Comprador;
import com.agroWeb.repository.CompradorRepository;
import com.agroWeb.service.exception.ImpossivelExcluirEntidadeException;
import com.agroWeb.service.exception.NomeCompradorJaCadastradoException;

@Service
public class CadastroCompradorService {

	@Autowired
	private CompradorRepository compradorRepository;

	@Transactional
	public Comprador salvar(Comprador comprador) {

		Optional<Comprador> compradorOptional = compradorRepository.findByNomeIgnoreCase(comprador.getNome());

		if (compradorOptional.isPresent() && comprador.isNova()) {
			throw new NomeCompradorJaCadastradoException("Comprador já existe");
		}

		return compradorRepository.saveAndFlush(comprador);

	}

	@Transactional
	public void excluir(Long id) {
		try {
			compradorRepository.delete(id);
			compradorRepository.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível excluir o comprador, o mesmo já foi utilizado");
		}

	}

}
