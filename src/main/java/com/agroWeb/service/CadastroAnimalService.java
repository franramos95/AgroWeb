package com.agroWeb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agroWeb.model.Animal;
import com.agroWeb.repository.AnimalRepository;
import com.agroWeb.service.exception.IdBrincoJaCadastradoException;

@Service
public class CadastroAnimalService {

	@Autowired
	public AnimalRepository animalRepository;

	@Transactional
	public Animal salvar(Animal animal) {

		Optional<Animal> animalOption = animalRepository.findByIdBrinco(animal.getIdBrinco());

		if (animalOption.isPresent()) {
			throw new IdBrincoJaCadastradoException("Animal já cadastrado com este Id Brinco!");
		}

		return animalRepository.saveAndFlush(animal);
	}
}
