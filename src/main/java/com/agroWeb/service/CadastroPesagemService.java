package com.agroWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agroWeb.model.Pesagem;
import com.agroWeb.repository.PesagemRepository;




@Service
public class CadastroPesagemService {
	@Autowired
	private PesagemRepository pesagemRepository ;

	@Transactional
	public Pesagem salva(Pesagem pesagem) {		
	return pesagemRepository.saveAndFlush(pesagem);
	}

	}


