package com.agroWeb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agroWeb.model.Dieta;
import com.agroWeb.repository.DietaRepository;
import com.agroWeb.service.exception.NomeDietaJaCadastradoException;

@Service
public class CadastroDietaService {

	@Autowired
	public DietaRepository dietaRepository;

	@Transactional
	public Dieta salvar(Dieta dieta) {

		Optional<Dieta> dietaOption = dietaRepository.findByNome(dieta.getNome());

		if (dietaOption.isPresent()) {
			throw new NomeDietaJaCadastradoException("Dieta já cadastrada");
		}
		return dietaRepository.saveAndFlush(dieta);
	}

}
