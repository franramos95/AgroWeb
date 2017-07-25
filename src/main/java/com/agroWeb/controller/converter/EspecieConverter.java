package com.agroWeb.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.agroWeb.model.Especie;

public class EspecieConverter implements Converter<String, Especie> {

	@Override
	public Especie convert(String id) {
		if (!StringUtils.isEmpty(id)) {
			Especie especie = new Especie();
			especie.setId(Long.valueOf(id));
			return especie;
		}

		return null;
	}
}
