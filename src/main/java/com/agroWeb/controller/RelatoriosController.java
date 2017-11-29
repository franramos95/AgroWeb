package com.agroWeb.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.agroWeb.dto.NomeDieta;
import com.agroWeb.dto.PeriodoRelatorio;
import com.agroWeb.repository.DietaRepository;

@Controller
@RequestMapping("/relatorios")
public class RelatoriosController {

	@Autowired
	private DietaRepository dietaRepository;

	@GetMapping("/despesas")
	public ModelAndView relatorioDespesa() {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioDespesas");
		mv.addObject(new PeriodoRelatorio());
		return mv;
	}

	@PostMapping("/despesas")
	public ModelAndView geraRelatorioDespesa(PeriodoRelatorio periodoRelatorio) {
		Map<String, Object> parametros = new HashMap<>();

		Date dataInicio = Date.from(LocalDateTime.of(periodoRelatorio.getDataInicio(), LocalTime.of(0, 0, 0))
				.atZone(ZoneId.systemDefault()).toInstant());
		Date dataFim = Date.from(LocalDateTime.of(periodoRelatorio.getDataFim(), LocalTime.of(23, 59, 59))
				.atZone(ZoneId.systemDefault()).toInstant());

		parametros.put("format", "pdf");
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFim);

		return new ModelAndView("relatorio_despesas", parametros);
	}

	@GetMapping("/vacinas")
	public ModelAndView relatorioVacinas() {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioVacinas");
		mv.addObject(new PeriodoRelatorio());
		return mv;
	}

	@PostMapping("/vacinas")
	public ModelAndView geraRelatorioVacina(PeriodoRelatorio periodoRelatorio) {
		Map<String, Object> parametros = new HashMap<>();

		Date dataInicio = Date.from(LocalDateTime.of(periodoRelatorio.getDataInicio(), LocalTime.of(0, 0, 0))
				.atZone(ZoneId.systemDefault()).toInstant());
		Date dataFim = Date.from(LocalDateTime.of(periodoRelatorio.getDataFim(), LocalTime.of(23, 59, 59))
				.atZone(ZoneId.systemDefault()).toInstant());

		parametros.put("format", "pdf");
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFim);

		return new ModelAndView("relatorio_vacinas", parametros);
	}

	@GetMapping("/ingredientes")
	public ModelAndView relatorioIngredientes() {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioIngredientes");
		mv.addObject(new PeriodoRelatorio());
		return mv;
	}

	@PostMapping("/ingredientes")
	public ModelAndView geraRelatorioIngrediente(PeriodoRelatorio periodoRelatorio) {
		Map<String, Object> parametros = new HashMap<>();

		Date dataInicio = Date.from(LocalDateTime.of(periodoRelatorio.getDataInicio(), LocalTime.of(0, 0, 0))
				.atZone(ZoneId.systemDefault()).toInstant());
		Date dataFim = Date.from(LocalDateTime.of(periodoRelatorio.getDataFim(), LocalTime.of(23, 59, 59))
				.atZone(ZoneId.systemDefault()).toInstant());

		parametros.put("format", "pdf");
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFim);

		return new ModelAndView("relatorio_ingrediente", parametros);
	}

	@GetMapping("/abatidos")
	public ModelAndView relatorioAnimaisAbatidos() {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioAnimalAbatido");
		mv.addObject(new PeriodoRelatorio());
		return mv;
	}

	@PostMapping("/abatidos")
	public ModelAndView geraRelatorioAnimalAbatido(PeriodoRelatorio periodoRelatorio) {
		Map<String, Object> parametros = new HashMap<>();

		Date dataInicio = Date.from(LocalDateTime.of(periodoRelatorio.getDataInicio(), LocalTime.of(0, 0, 0))
				.atZone(ZoneId.systemDefault()).toInstant());
		Date dataFim = Date.from(LocalDateTime.of(periodoRelatorio.getDataFim(), LocalTime.of(23, 59, 59))
				.atZone(ZoneId.systemDefault()).toInstant());

		parametros.put("format", "pdf");
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFim);

		return new ModelAndView("relatorio_animal_abatido", parametros);
	}
	
	@GetMapping("/dietas")
	public ModelAndView relatorioDietasAnimal() {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioDietaAnimal");
		mv.addObject(new NomeDieta());
		mv.addObject("dietas", dietaRepository.findAll());
		return mv;
	}

	@PostMapping("/dietas")
	public ModelAndView geraRelatorioDietasAnimal(NomeDieta nomeDieta, BindingResult result) {
		Map<String, Object> parametros = new HashMap<>();

		// Dieta dieta = new Dieta();

		parametros.put("format", "pdf");
		parametros.put("dieta", dietaRepository.findAll());

		return new ModelAndView("relatorio_dieta_animal", parametros);
	}
	
	@GetMapping("/vendas")
	public ModelAndView relatorioVendas() {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioVenda");
		mv.addObject(new PeriodoRelatorio());
		return mv;
	}

	@PostMapping("/vendas")
	public ModelAndView geraRelatorioVendas(PeriodoRelatorio periodoRelatorio) {
		Map<String, Object> parametros = new HashMap<>();

		Date dataInicio = Date.from(LocalDateTime.of(periodoRelatorio.getDataInicio(), LocalTime.of(0, 0, 0))
				.atZone(ZoneId.systemDefault()).toInstant());
		Date dataFim = Date.from(LocalDateTime.of(periodoRelatorio.getDataFim(), LocalTime.of(23, 59, 59))
				.atZone(ZoneId.systemDefault()).toInstant());

		parametros.put("format", "pdf");
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFim);

		return new ModelAndView("relatorio_vendas", parametros);
	}

}
