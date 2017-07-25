package com.agroWeb.repository.helper.venda;

import java.time.LocalDateTime;
import java.time.LocalTime;

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

import com.agroWeb.model.Venda;
import com.agroWeb.repository.filter.VendaFilter;
import com.agroWeb.repository.paginacao.PaginacaoUtil;

public class VendaRepositoryImpl implements VendaRepositoryQueries{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Venda> filtrar (VendaFilter filter, Pageable pageable){
		   Criteria criteria = manager.unwrap(Session.class).createCriteria(Venda.class);
		   
	paginacaoUtil.preparar(criteria, pageable);
	adicionarFiltro(filter, criteria);
	
	return new PageImpl<>(criteria.list(), pageable, total(filter));	
	}
	
	private Long total(VendaFilter filter){
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Venda.class);
		adicionarFiltro(filter,criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(VendaFilter filter, Criteria criteria){
		criteria.createAlias("comprador", "c");
		
		if (filter != null){
			if (filter.getArroba() != null) {
				criteria.add(Restrictions.eq("arroba", filter.getArroba()));
			}
			if (filter.getValorArroba() != null) {
				criteria.add(Restrictions.eq("valor arroba", filter.getValorArroba()));
			}
			if (filter.getValorTotal() != null) {
				criteria.add(Restrictions.eq("valor total", filter.getValorArroba()));
			}
			if (filter.getDataVenda() != null){
				LocalDateTime dtvenda = LocalDateTime.of(filter.getDataVenda(), LocalTime.of(23, 59));
				criteria.add(Restrictions.ge("dataVenda", dtvenda));
			}
			if (!StringUtils.isEmpty(filter.getNomeComprador())){
				criteria.add(Restrictions.ilike("c.nome", filter.getNomeComprador(), MatchMode.ANYWHERE));
			}
		   
		    }

	
	}
	
	
	
}
