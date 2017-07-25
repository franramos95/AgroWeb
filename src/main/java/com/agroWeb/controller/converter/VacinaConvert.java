package com.agroWeb.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.agroWeb.model.Vacina;

public class VacinaConvert implements Converter<String, Vacina>{
	
	@Override
	public Vacina convert(String id){
		if(!StringUtils.isEmpty(id)){
			Vacina vacina = new Vacina();
			vacina.setId(Long.valueOf(id));
			return vacina;
		}
		return null;
	}

}
