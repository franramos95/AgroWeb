package com.agroWeb.repository.helper.animal;

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

import com.agroWeb.model.Animal;
import com.agroWeb.repository.filter.AnimalFilter;
import com.agroWeb.repository.paginacao.PaginacaoUtil;

public class AnimalRepositoryImpl implements AnimalRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Animal> filtrar(AnimalFilter filter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Animal.class);

		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filter, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filter));
	}

	private Long total(AnimalFilter filter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Animal.class);
		adicionarFiltro(filter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro (AnimalFilter filter, Criteria criteria){
		if(filter != null){
			if(!StringUtils.isEmpty(filter.getNome())){
				criteria.add(Restrictions.ilike("nome", filter.getNome(), MatchMode.ANYWHERE));
			}
			if (filter.getIdBrinco() != null) {
				criteria.add(Restrictions.eq("id brinco", filter.getIdBrinco()));
			}
			if (filter.getEspecie() != null) {
				criteria.add(Restrictions.eq("especie", filter.getEspecie()));
			}
			if (filter.getLote() != null) {
				criteria.add(Restrictions.eq("lote", filter.getLote()));
			}
			if (filter.getSituacaoAnimal() != null){
				criteria.add(Restrictions.eq("situação", filter.getSituacaoAnimal()));
			}


		}
	}
}