package com.agroWeb.repository.helper.cidade;

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

import com.agroWeb.model.Cidade;
import com.agroWeb.repository.filter.CidadeFilter;
import com.agroWeb.repository.paginacao.PaginacaoUtil;

public class CidadeRepositoryImpl implements CidadeRepositoryQueries{
	@PersistenceContext
	 private EntityManager entityManager;

	 @Autowired
	 PaginacaoUtil paginacaoUtil;

	 @SuppressWarnings("unchecked")
	 @Override
	 @Transactional(readOnly = true)
	 public Page<Cidade> filtrar(CidadeFilter filter, Pageable pageable) {
	  Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Cidade.class);

	  paginacaoUtil.preparar(criteria, pageable);
	  adicionaFiltro(filter, criteria);
	  criteria.createAlias("estado", "e");
	  
	  return new PageImpl<>(criteria.list(), pageable, total(filter));
	 }
	 
	 private void adicionaFiltro(CidadeFilter filter, Criteria criteria) {

	  if (filter != null) {
	   if (!StringUtils.isEmpty(filter.getNome())) {
	    criteria.add(Restrictions.ilike("nome", filter.getNome(), MatchMode.ANYWHERE));
	   }

	   if (!StringUtils.isEmpty(filter.getEstado())) {
	    criteria.add(Restrictions.eq("estado", filter.getEstado()));
	   }
	  }

	 } 
	 
	 private Long total(CidadeFilter filter) {
	  Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Cidade.class);
	  adicionaFiltro(filter, criteria);
	  criteria.setProjection(Projections.rowCount());
	  return (Long) criteria.uniqueResult();
	 }

}
