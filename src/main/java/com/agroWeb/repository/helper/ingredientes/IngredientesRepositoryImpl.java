package com.agroWeb.repository.helper.ingredientes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.agroWeb.dto.IngredientesDTO;
import com.agroWeb.model.Ingrediente;
import com.agroWeb.repository.filter.IngredienteFilter;
import com.agroWeb.repository.paginacao.PaginacaoUtil;

public class IngredientesRepositoryImpl implements IngredientesRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Ingrediente> filtrar(IngredienteFilter filter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Ingrediente.class);

		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filter, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filter));
	}

	private void adicionarFiltro(IngredienteFilter filter, Criteria criteria) {
		if (filter != null) {
			if (!StringUtils.isEmpty(filter.getNome())) {
				criteria.add(Restrictions.ilike("nome", filter.getNome(), MatchMode.ANYWHERE));
			}
			if (filter.getPreco() != null) {
				criteria.add(Restrictions.eq("preço", filter.getPreco()));
			}
		}

	}

	private Long total(IngredienteFilter filter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Ingrediente.class);
		adicionarFiltro(filter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();

	}

	@Override
	public List<IngredientesDTO> porIdOuNome(String idOuNome) {
		String jpql = "select new com.agroWeb.dto.IngredientesDTO(id,nome)"
				+ "from ingrediente where id like :idOuNome or lower(nome) like :idOuNome";
		List<IngredientesDTO> ingredientesFiltrados = manager.createQuery(jpql,IngredientesDTO.class)
				.setParameter(idOuNome, idOuNome.toLowerCase() + "%").getResultList();
		return ingredientesFiltrados;
	}
}
