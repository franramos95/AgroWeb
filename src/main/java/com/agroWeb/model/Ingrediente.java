package com.agroWeb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "ingrediente")
public class Ingrediente implements Serializable {

	private static final long serialVersionUID = -8566468295819914130L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome é obrigatório")
	private String nome;

	@NotNull(message = "O valor é obrigatório")
	@DecimalMin(value = "0.01", message = "O valor deve ser maior que 0")
	@DecimalMax(value = "999999.99", message = "O valor maximo deve ser 999.999,99")
	private BigDecimal valor;

	@NotNull(message = "Quantidade é obrigatória")
	@Min(value = 1, message = "O valor minímo deve ser maior ou igual a 1")
	@Max(value = 99999, message = "O valor máximo dever ser de até 99.999")
	private Long quantidade;

	@OneToOne
	@JoinColumn(name = "id_despesa")
	private Despesa despesa;

	private LocalDate vencimento;

	@NotNull(message = "O valor unitario é obrigatório")
	@DecimalMin(value = "0.01", message = "O valor deve ser maior que 0")
	@DecimalMax(value = "999999.99", message = "O valor maximo deve ser 999.999,99")
	@Column(name = "valor_unitario")
	private BigDecimal valorUnitario;

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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public LocalDate getVencimento() {
		return vencimento;
	}

	public void setVencimento(LocalDate vencimento) {
		this.vencimento = vencimento;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public boolean isNova() {
		return id == null;
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
		Ingrediente other = (Ingrediente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
