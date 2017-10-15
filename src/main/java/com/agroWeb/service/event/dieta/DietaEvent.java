package com.agroWeb.service.event.dieta;

import com.agroWeb.model.Dieta;

public class DietaEvent {

	private Dieta dieta;

	public DietaEvent(Dieta dieta) {
		this.dieta = dieta;
	}

	public Dieta getDieta() {
		return dieta;
	}

	

}
