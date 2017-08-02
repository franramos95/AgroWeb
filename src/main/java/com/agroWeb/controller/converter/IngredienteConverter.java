package com.agroWeb.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.agroWeb.model.Ingrediente;

public class IngredienteConverter implements Converter<String, Ingrediente> {

	@Override
	public Ingrediente convert(String id) {

		if (!StringUtils.isEmpty(id)) {
			Ingrediente ingrediente = new Ingrediente();
			ingrediente.setId(Long.valueOf(id));
			return ingrediente;
		}
		return null;

	}

}
