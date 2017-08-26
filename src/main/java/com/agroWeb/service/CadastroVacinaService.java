package com.agroWeb.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agroWeb.model.Despesa;
import com.agroWeb.model.TipoDespesa;
import com.agroWeb.model.Vacina;
import com.agroWeb.repository.DespesaRepository;
import com.agroWeb.repository.VacinaRepository;
import com.agroWeb.service.exception.ImpossivelExcluirEntidadeException;
import com.agroWeb.service.exception.NomeVacinaJaCadastradaException;


@Service
public class CadastroVacinaService {
	@Autowired
	private VacinaRepository vacinaRepository;
	
	@Autowired
	private CadastroDespesaService cadastroDespesaService;
	
	@Autowired
	private DespesaRepository despesaRepository;

	@Transactional
	public Vacina salva(Vacina vacina) {

		Optional<Vacina> vacinaOptional = vacinaRepository.findByNomeIgnoreCase(vacina.getNome());

		if (vacinaOptional.isPresent() && vacina.isNova()) {
			throw new NomeVacinaJaCadastradaException("Vacina já existente");
		}
		
		if(vacina.isNova()){
			try{
				Despesa despesa = new Despesa();
				despesa.setNome(vacina.getNome());
				despesa.setTipoDespesa(TipoDespesa.VACINA);
				despesa.setValor(vacina.getValor());
				despesa.setData(LocalDate.now());
				vacina.setDespesa(despesa);
				cadastroDespesaService.salva(despesa);
				return vacinaRepository.saveAndFlush(vacina);
			}catch(Exception e){
				throw new NomeVacinaJaCadastradaException("Vacina já existente");
			}
		}else {
			try{
				Despesa despesa = despesaRepository.findOne(vacina.getDespesa().getId());
				despesa.setNome(vacina.getNome());
				despesa.setTipoDespesa(TipoDespesa.VACINA);
				despesa.setValor(vacina.getValor());
				despesa.setData(LocalDate.now());
				cadastroDespesaService.salva(despesa);
				return vacinaRepository.saveAndFlush(vacina);
			}catch(Exception e){
				throw e;
			}
		}

		

	}

	@Transactional
	public void excluir(Long id) {
		try {
			Vacina vacina = vacinaRepository.findOne(id);
			vacinaRepository.delete(id);
			cadastroDespesaService.excluir(vacina.getDespesa().getId());
			vacinaRepository.flush();
		}catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível excluir a vacina");
		}
		
	}


}
