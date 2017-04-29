package com.agroWeb.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class AnimalDoencaId implements Serializable {

	private static final long serialVersionUID = -1120681855955887956L;

	@ManyToOne
	@JoinColumn(name = "id_doenca")
	private Doenca doenca;

	@ManyToOne
	@JoinColumn(name = "id_animal")
	private Animal animal;

	public Doenca getDoenca() {
		return doenca;
	}

	public void setDoenca(Doenca doenca) {
		this.doenca = doenca;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((animal == null) ? 0 : animal.hashCode());
		result = prime * result + ((doenca == null) ? 0 : doenca.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnimalDoencaId other = (AnimalDoencaId) obj;
		if (animal == null) {
			if (other.animal != null)
				return false;
		} else if (!animal.equals(other.animal))
			return false;
		if (doenca == null) {
			if (other.doenca != null)
				return false;
		} else if (!doenca.equals(other.doenca))
			return false;
		return true;
	}

}
