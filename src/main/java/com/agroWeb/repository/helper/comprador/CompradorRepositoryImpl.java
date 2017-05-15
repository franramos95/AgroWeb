package com.agroWeb.repository.helper.comprador;

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

import com.agroWeb.model.Comprador;
import com.agroWeb.repository.filter.CompradorFilter;
import com.agroWeb.repository.paginacao.PaginacaoUtil;

public class CompradorRepositoryImpl implements CompradorRepositoryQueries  {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Comprador> filtrar (CompradorFilter filter, Pageable pageable){
		Criteria criteria =  manager.unwrap(Session.class).createCriteria(Comprador.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filter, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filter));
	}
	 
	private void adicionarFiltro(CompradorFilter filter, Criteria criteria){
		if (filter != null){
			if(!StringUtils.isEmpty(filter.getNome())) {
				criteria.add(Restrictions.ilike("nome", filter.getNome(), MatchMode.ANYWHERE));
				}
			
			if(!StringUtils.isEmpty(filter.getCpfOuCnpj())) {
				criteria.add(Restrictions.eq("cpfOuCnpj", filter.getCpfOuCnpj()));
			}
		    
		    }
	}
	private Long total(CompradorFilter filter){
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Comprador.class);
		adicionarFiltro(filter,criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
}
