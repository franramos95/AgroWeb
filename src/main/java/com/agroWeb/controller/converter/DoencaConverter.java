package com.agroWeb.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.agroWeb.model.Doenca;

public class DoencaConverter implements Converter<String, Doenca>{
	
	@Override
	public Doenca convert(String id){
		if(!StringUtils.isEmpty(id)){
			Doenca doenca = new Doenca();
			doenca.setId(Long.valueOf(id));
			return doenca;
		}
		return null;
	}

}
