package com.agroWeb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "dieta")
public class Dieta implements Serializable {

	private static final long serialVersionUID = 6212153225423196053L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome da dieta é obrigatória")
	private String nome;

	@NotBlank(message = "O tipo da dieta é obrigatória")
	private String tipo;

	@OneToMany(mappedBy = "dieta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemDieta> itens = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<ItemDieta> getItens() {
		return itens;
	}

	public void setItens(List<ItemDieta> itens) {
		this.itens = itens;
	}
	
	public boolean isNovo(){
		return id == null;
	}
	
	public void adicionarItens(List<ItemDieta> itens) {
		
		this.itens = itens;
		this.itens.forEach(i -> i.setDieta(this));
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Dieta other = (Dieta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
