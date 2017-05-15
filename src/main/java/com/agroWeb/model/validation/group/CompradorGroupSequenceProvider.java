package com.agroWeb.model.validation.group;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.agroWeb.model.Comprador;

public class CompradorGroupSequenceProvider implements DefaultGroupSequenceProvider<Comprador> {
	
	@Override
	 public List<Class<?>> getValidationGroups(Comprador comprador) {
	  List<Class<?>> grupos = new ArrayList<>();
	  grupos.add(Comprador.class);
	  
	  if (isPessoaSelecionada(comprador)){
	   grupos.add(comprador.getTipoPessoa().getGrupo());
	  }
	  
	  return grupos;
	 }

	 private boolean isPessoaSelecionada(Comprador comprador) {
	  return comprador != null && comprador.getTipoPessoa() != null;
	 }

}
