package com.agroWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agroWeb.model.Venda;
import com.agroWeb.repository.VendaRepository;

@Service
public class CadastroVendaService {

	@Autowired
	private VendaRepository vendaRepository;
	
	@Transactional
	public void salva(Venda venda){
		vendaRepository.saveAndFlush(venda);
	}
}
