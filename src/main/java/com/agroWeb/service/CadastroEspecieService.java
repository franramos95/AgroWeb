package com.agroWeb.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.agroWeb.model.Especie;
import com.agroWeb.repository.EspecieRepository;
import com.agroWeb.service.exception.ImpossivelExcluirEntidadeException;
import com.agroWeb.service.exception.NomeEspecieJaCadastradaException;

@Service
public class CadastroEspecieService {
	
	@Autowired
	private EspecieRepository especieRepository;
	
	@Transactional
	public Especie salvar(Especie especie){
		
		Optional<Especie> especieOptional = especieRepository.findByNomeIgnoreCase(especie.getNome());
		
		
		if (especieOptional.isPresent()){
			throw new NomeEspecieJaCadastradaException("Espécie com este nome já cadastrada");
		}
		
		return especieRepository.saveAndFlush(especie);
	}	
	
	@Transactional
	public void excluir(Long id){
		try{
			especieRepository.delete(id);
			especieRepository.flush();
			} catch (PersistenceException e){
				throw new ImpossivelExcluirEntidadeException("Impossível excluir a especie, a mesma foi utilizada em um animal");
			}
	}

}
