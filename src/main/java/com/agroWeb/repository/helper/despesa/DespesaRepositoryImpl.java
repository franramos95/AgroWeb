package com.agroWeb.repository.helper.despesa;

import java.math.BigDecimal;
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

import com.agroWeb.dto.DespesaMes;
import com.agroWeb.model.Despesa;
import com.agroWeb.repository.filter.DespesaFilter;
import com.agroWeb.repository.paginacao.PaginacaoUtil;

public class DespesaRepositoryImpl implements DespesaRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Despesa> filtrar(DespesaFilter filter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Despesa.class);

		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filter, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filter));
	}

	@Override
	public BigDecimal totalDeDespesa() {
		Optional<BigDecimal> optional = Optional
				.ofNullable(manager.createQuery("select sum(valor) from Despesa", BigDecimal.class).getSingleResult());
		return optional.orElse(BigDecimal.ZERO);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<DespesaMes> totalPorMes() {
		List<DespesaMes> despesaMes = manager.createNamedQuery("Despesa.totalPorMes").getResultList();
		
		LocalDate hoje =  LocalDate.now();
		for (int i =1; i <= 6; i++){
			String mesIdeal = String.format("%d/%02d", hoje.getYear(), hoje.getMonthValue());
			
			boolean possuiMes = despesaMes.stream().filter(v -> v.getMes().equals(mesIdeal)).findAny().isPresent();
			if(!possuiMes){
				despesaMes.add(i - 1, new DespesaMes(mesIdeal, 0));
			}
			
			hoje = hoje.minusMonths(1);
		}
		return despesaMes;
	}

	public void adicionarFiltro(DespesaFilter filter, Criteria criteria) {
		if (filter != null) {
			if (!StringUtils.isEmpty(filter.getNome())) {
				criteria.add(Restrictions.ilike("nome", filter.getNome(), MatchMode.ANYWHERE));
			}
			if (!StringUtils.isEmpty(filter.getTipoDespesa())) {
				criteria.add(Restrictions.eq("tipoDespesa", filter.getTipoDespesa()));
			}
			if (filter.getValor() != null) {
				criteria.add(Restrictions.eq("valor", filter.getValor()));
			}
			if (filter.getDesde() != null) {
				LocalDate desde = LocalDate.of(filter.getDesde().getYear(), filter.getDesde().getMonth(),
						filter.getDesde().getDayOfMonth());
				// LocalDateTime desde = LocalDateTime.of(filter.getDesde(),
				// LocalTime.of(0, 0));
				criteria.add(Restrictions.ge("data", desde));
			}
			if (filter.getAte() != null) {
				LocalDate ate = LocalDate.of(filter.getAte().getYear(), filter.getAte().getMonth(),
						filter.getAte().getDayOfMonth());
				// LocalDate ate = LocalDate.from(filter.getAte());
				// LocalDateTime ate = LocalDateTime.of(filter.getAte(),
				// LocalTime.of(23, 59));
				criteria.add(Restrictions.le("data", ate));
			}
		}
	}

	private Long total(DespesaFilter filter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Despesa.class);
		adicionarFiltro(filter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

}
