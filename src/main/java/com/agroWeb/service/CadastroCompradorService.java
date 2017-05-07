package com.agroWeb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agroWeb.model.Comprador;
import com.agroWeb.repository.CompradorRepository;
import com.agroWeb.service.exception.NomeCompradorJaCadastradoException;

@Service
public class CadastroCompradorService {

	@Autowired
	private CompradorRepository compradorRepository;

	@Transactional
	public Comprador salvar(Comprador comprador) {

		Optional<Comprador> compradorOptional = compradorRepository.findByNomeIgnoreCase(comprador.getNome());

		if (compradorOptional.isPresent()) {
			throw new NomeCompradorJaCadastradoException("Comprador já existe");
		}

		return compradorRepository.saveAndFlush(comprador);

	}

}
