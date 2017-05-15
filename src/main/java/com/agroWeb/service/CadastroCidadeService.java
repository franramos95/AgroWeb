package com.agroWeb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agroWeb.model.Cidade;
import com.agroWeb.repository.CidadeRepository;
import com.agroWeb.service.exception.CidadeJaExistenteException;

@Service
public class CadastroCidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Transactional	
	public void salvar(Cidade cidade){
		
		Optional<Cidade> cidadeExistente = cidadeRepository.findByNomeAndEstado(cidade.getNome(), cidade.getEstado());
		
		if(cidadeExistente.isPresent()){
			throw new CidadeJaExistenteException("Cidade já cadastrada para este estado");
		}
		
		cidadeRepository.save(cidade);
		
	}

}
