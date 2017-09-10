package com.agroWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.agroWeb.repository.AnimalRepository;
import com.agroWeb.repository.CompradorRepository;
import com.agroWeb.repository.DespesaRepository;
import com.agroWeb.repository.IngredientesRepository;

@Controller
public class DashboardController {
	
	@Autowired
	private IngredientesRepository ingrediente;
	
	@Autowired
	private AnimalRepository animal;
	
	@Autowired
	private CompradorRepository comprador;
	
	@Autowired
	private DespesaRepository despesa;

	@GetMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("Dashboard");

		mv.addObject("valorTotalIngrediente", ingrediente.valorTotalIngrediente());
		mv.addObject("totalDeAnimais", animal.totalDeAnimais());
		mv.addObject("totalDeCompradores", comprador.totalDeCompradores());
		mv.addObject("totalDeDespesa", despesa.totalDeDespesa());
		
		
		return mv;
	}
}
