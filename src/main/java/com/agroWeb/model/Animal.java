package com.agroWeb.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "animal")
public class Animal implements Serializable {

	private static final long serialVersionUID = -5085488148537269956L;

	@Id
	@Column(name = "id_brinco")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBrinco;

	@NotBlank(message = "O nome do animal é obrigatório")
	private String nome;

	@NotNull(message = "A especie é obrigatória")
	private Especie especie;

	@NotNull(message = "O lote é obrigatório")
	private Long lote;

	@NotNull(message = "o sexo é obrigatório")
	private Sexo sexo;

	@NotNull(message = "A idade é obrigatória")
	private Integer idade;

	@NotNull(message = "A data de nascimento é obrigatória")
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	@NotNull(message = "A data de aquisição é obrigatória")
	@Column(name = "data_aquisicao")
	private LocalDate dataAquisicao;

	@NotNull(message = "A situação do animal é obrigatória")
	@Column(name = "situacao_animal")
	private SituacaoAnimal situacaoAnimal;
	
	@ManyToMany
	@JoinTable(name = "animal_vacina", joinColumns = @JoinColumn(name = "id_animal"), inverseJoinColumns = @JoinColumn(name = "id_vacina"))
	private List<Vacina> vacinas;

	@NotNull(message = "A dieta é obrigatória")
	@ManyToOne
	@JoinColumn(name = "id_dieta")
	private Dieta dieta;

	@ManyToMany
	@JoinTable(name = "animal_doenca", joinColumns = @JoinColumn(name = "id_animal"), inverseJoinColumns = @JoinColumn(name = "id_doenca"))
	private List<Doenca> doencas;

	public Long getIdBrinco() {
		return idBrinco;
	}

	public void setIdBrinco(Long idBrinco) {
		this.idBrinco = idBrinco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public Long getLote() {
		return lote;
	}

	public void setLote(Long lote) {
		this.lote = lote;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public LocalDate getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(LocalDate dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public SituacaoAnimal getSituacaoAnimal() {
		return situacaoAnimal;
	}

	public void setSituacaoAnimal(SituacaoAnimal situacaoAnimal) {
		this.situacaoAnimal = situacaoAnimal;
	}

	public List<Vacina> getVacinas() {
		return vacinas;
	}

	public void setVacinas(List<Vacina> vacinas) {
		this.vacinas = vacinas;
	}

	public Dieta getDieta() {
		return dieta;
	}

	public void setDieta(Dieta dieta) {
		this.dieta = dieta;
	}

	public List<Doenca> getDoencas() {
		return doencas;
	}

	public void setDoencas(List<Doenca> doencas) {
		this.doencas = doencas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idBrinco == null) ? 0 : idBrinco.hashCode());
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
		Animal other = (Animal) obj;
		if (idBrinco == null) {
			if (other.idBrinco != null)
				return false;
		} else if (!idBrinco.equals(other.idBrinco))
			return false;
		return true;
	}
	
	

}
