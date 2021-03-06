package com.agroWeb.repository.helper.venda;

import java.time.LocalDate;

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
import org.springframework.util.StringUtils;

import com.agroWeb.model.Venda;
import com.agroWeb.repository.filter.VendaFilter;
import com.agroWeb.repository.paginacao.PaginacaoUtil;

public class VendaRepositoryImpl implements VendaRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Venda> filtrar(VendaFilter filter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Venda.class);

		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filter, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filter));
	}

	private Long total(VendaFilter filter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Venda.class);
		adicionarFiltro(filter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(VendaFilter filter, Criteria criteria) {
		criteria.createAlias("comprador", "c");


		if (filter != null) {
		}
		if (!StringUtils.isEmpty(filter.getNome())) {
			criteria.add(Restrictions.ilike("nome", filter.getNome()));
		}
		if (filter.getValor() != null) {
			criteria.add(Restrictions.eq("valor", filter.getValor()));
		}
		if (filter.getDesde() != null) {
			LocalDate desde = LocalDate.of(filter.getDesde().getYear(), filter.getDesde().getMonth(),
					filter.getDesde().getDayOfMonth());
			// LocalDateTime desde = LocalDateTime.of(filter.getDesde(),
			// LocalTime.of(0, 0));
			criteria.add(Restrictions.ge("dataCriacao", desde));
		}
		if (filter.getAte() != null) {
			LocalDate ate = LocalDate.of(filter.getAte().getYear(), filter.getAte().getMonth(),
					filter.getAte().getDayOfMonth());
			// LocalDate ate = LocalDate.from(filter.getAte());
			// LocalDateTime ate = LocalDateTime.of(filter.getAte(),
			// LocalTime.of(23, 59));
			criteria.add(Restrictions.le("dataCriacao", ate));
		}
		if (!StringUtils.isEmpty(filter.getComprador())) {
			criteria.add(Restrictions.ilike("c.nome", filter.getComprador()));
		}
		if (!StringUtils.isEmpty(filter.getStatus())) {
			criteria.add(Restrictions.ilike("status", filter.getStatus()));
		}
	}

}
