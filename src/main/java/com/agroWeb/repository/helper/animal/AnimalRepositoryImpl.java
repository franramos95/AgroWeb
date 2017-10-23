package com.agroWeb.repository.helper.animal;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

import com.agroWeb.dto.MortalidadeMes;
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

	@Override
	public Long totalDeAnimais() {
		Optional<Long> optional = Optional
				.ofNullable(manager.createQuery("select count(*) from Animal", Long.class).getSingleResult());
		return optional.orElse(0L);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MortalidadeMes> totalPorMes() {
		List<MortalidadeMes> mortalidadeMes = manager.createNamedQuery("Animal.mortosPorMes").getResultList();

		LocalDate hoje = LocalDate.now();
		for (int i = 1; i <= 6; i++) {
			String mesIdeal = String.format("%d/%02d", hoje.getYear(), hoje.getMonthValue());

			boolean possuiMes = mortalidadeMes.stream().filter(v -> v.getMes().equals(mesIdeal)).findAny().isPresent();
			if (!possuiMes) {
				mortalidadeMes.add(i - 1, new MortalidadeMes(mesIdeal, 0));
			}

			hoje = hoje.minusMonths(1);
		}
		return mortalidadeMes;
	}

	private Long total(AnimalFilter filter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Animal.class);
		adicionarFiltro(filter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(AnimalFilter filter, Criteria criteria) {
		if (filter != null) {
			if (!StringUtils.isEmpty(filter.getNome())) {
				criteria.add(Restrictions.ilike("nome", filter.getNome(), MatchMode.ANYWHERE));
			}
			if (filter.getIdBrinco() != null) {
				criteria.add(Restrictions.eq("idBrinco", filter.getIdBrinco()));
			}
			if (filter.getEspecie() != null) {
				criteria.add(Restrictions.eq("especie", filter.getEspecie()));
			}
			if (filter.getLote() != null) {
				criteria.add(Restrictions.eq("lote", filter.getLote()));
			}
			if (filter.getSituacao() != null) {
				criteria.add(Restrictions.eq("situacao", filter.getSituacao()));
			}

		}
	}

}