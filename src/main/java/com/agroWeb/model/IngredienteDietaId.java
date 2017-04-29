package com.agroWeb.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class IngredienteDietaId implements Serializable {

	private static final long serialVersionUID = -1883963924756597235L;

	@ManyToOne
	@JoinColumn(name = "id_dieta")
	private Dieta dieta;

	@ManyToOne
	@JoinColumn(name = "id_ingradiente")
	private Ingrediente ingrediente;

	public Dieta getDieta() {
		return dieta;
	}

	public void setDieta(Dieta dieta) {
		this.dieta = dieta;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

}
