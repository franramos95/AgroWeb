package com.agroWeb.repository.helper.pesagem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;


import com.agroWeb.model.Pesagem;
import com.agroWeb.repository.filter.PesagemFilter;
import com.agroWeb.repository.paginacao.PaginacaoUtil;


public class PesagemRepositoryImpl {
	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<Pesagem> filtrar(PesagemFilter filter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Pesagem.class);

		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filter, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filter));
	}
	
	private void adicionarFiltro(PesagemFilter filter, Criteria criteria) {
		if (filter.getData() != null) {
			criteria.add(Restrictions.eq("Data", filter.getData()));
		}
		if (filter.getPeso() != null) {
			criteria.add(Restrictions.eq("Peso", filter.getPeso()));
		}
	}	
	
	
	private Long total(PesagemFilter filter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Pesagem.class);
		adicionarFiltro(filter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}		



}
