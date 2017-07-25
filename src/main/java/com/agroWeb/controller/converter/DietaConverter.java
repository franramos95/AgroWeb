package com.agroWeb.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.agroWeb.model.Dieta;

public class DietaConverter implements Converter<String, Dieta>{
	
	@Override
	public Dieta convert(String id){
		if (!StringUtils.isEmpty(id)){
			Dieta dieta = new Dieta();
			dieta.setId(Long.valueOf(id));
			return dieta;
		}
	return null;
	}

}
