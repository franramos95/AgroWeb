package com.agroWeb.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agroWeb.model.Animal;
import com.agroWeb.model.SituacaoAnimal;
import com.agroWeb.repository.AnimalRepository;
import com.agroWeb.service.exception.IdBrincoJaCadastradoException;
import com.agroWeb.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroAnimalService {

	@Autowired
	public AnimalRepository animalRepository;

	@Transactional
	public Animal salvar(Animal animal) {

		Optional<Animal> animalOption = animalRepository.findByIdBrinco(animal.getIdBrinco());

		if (animalOption.isPresent() && animal.isNova()) {
			throw new IdBrincoJaCadastradoException("Animal já cadastrado com este Id Brinco!");
		}
		if ((animal.getSituacao() == SituacaoAnimal.ABATIDO)) {
			animal.setDataAlteracao(LocalDate.now());
		}

		if ((animal.getSituacao() == SituacaoAnimal.MORTO)) {
			animal.setDataAlteracao(LocalDate.now());
		}
		return animalRepository.saveAndFlush(animal);
	}

	@Transactional
	public void excluir(Long id) {
		try {
			animalRepository.delete(id);
			animalRepository.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossivel excluir o animal");
		}

	}
}
