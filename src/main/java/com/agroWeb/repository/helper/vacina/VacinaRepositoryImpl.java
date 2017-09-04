package com.agroWeb.repository.helper.vacina;

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

import com.agroWeb.model.Vacina;
import com.agroWeb.repository.filter.VacinaFilter;
import com.agroWeb.repository.paginacao.PaginacaoUtil;

public class VacinaRepositoryImpl implements VacinaRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Vacina> filtrar(VacinaFilter filter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Vacina.class);

		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filter, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filter));
	}

	private void adicionarFiltro(VacinaFilter filter, Criteria criteria) {
		if (filter != null) {
			if (!StringUtils.isEmpty(filter.getNome())) {
				criteria.add(Restrictions.ilike("nome", filter.getNome(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(filter.getLote())) {
				criteria.add(Restrictions.ilike("lote", filter.getLote(), MatchMode.ANYWHERE));
			}
			if (filter.getData() != null) {
				criteria.add(Restrictions.eq("data", filter.getData()));
			}
			if (filter.getVencimento() != null) {
				criteria.add(Restrictions.eq("vencimento", filter.getVencimento()));
			}
			if (filter.getValor() != null) {
				criteria.add(Restrictions.eq("valor", filter.getValor()));
			}
		}

	}

	private Long total(VacinaFilter filter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Vacina.class);
		adicionarFiltro(filter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();

	}

}
