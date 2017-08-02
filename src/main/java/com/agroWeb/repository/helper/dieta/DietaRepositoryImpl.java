package com.agroWeb.repository.helper.dieta;

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

import com.agroWeb.model.Dieta;
import com.agroWeb.repository.filter.DietaFilter;
import com.agroWeb.repository.paginacao.PaginacaoUtil;

public class DietaRepositoryImpl {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<Dieta> filtrar (DietaFilter filter, Pageable pageable){
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Dieta.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filter, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filter));
	}
	
	private Long total(DietaFilter filter){
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Dieta.class);
		adicionarFiltro(filter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(DietaFilter filter, Criteria criteria){
		if(filter != null){
			if (!StringUtils.isEmpty(filter.getNome())) {
				criteria.add(Restrictions.ilike("nome", filter.getNome(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(filter.getTipo())) {
				criteria.add(Restrictions.ilike("tipo", filter.getTipo(), MatchMode.ANYWHERE));
			}
		}
	}
	
}
