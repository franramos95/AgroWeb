package com.agroWeb.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agroWeb.controller.page.PageWrapper;
import com.agroWeb.model.Ingrediente;
import com.agroWeb.repository.IngredientesRepository;
import com.agroWeb.repository.filter.IngredienteFilter;
import com.agroWeb.service.CadastroIngredientesService;
import com.agroWeb.service.exception.NomeIngredienteJaCadastradaException;

@Controller
@RequestMapping("/ingredientes")
public class IngredientesController {

	@Autowired
	private CadastroIngredientesService cadastroIngredientesService;

	@Autowired
	private IngredientesRepository ingredientesRepository;

	@RequestMapping("/novo")
	public ModelAndView novo(Ingrediente ingrediente) {
		ModelAndView mv = new ModelAndView("ingrediente/CadastroIngrediente");
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Ingrediente ingrediente, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(ingrediente);
		}
		try {
			cadastroIngredientesService.salva(ingrediente);
		} catch (NomeIngredienteJaCadastradaException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(ingrediente);
		}
		attributes.addFlashAttribute("mensagem", "Ingrediente salvo com sucesso!");
		return new ModelAndView("redirect:/ingredientes/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(IngredienteFilter filter, BindingResult result, @PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest){
		ModelAndView mv = new ModelAndView("ingrediente/PesquisaIngrediente");
	
		PageWrapper<Ingrediente> pageWrapper = new PageWrapper<>(ingredientesRepository.filtrar(filter, pageable), httpServletRequest);
		mv.addObject("pagina", pageWrapper);;
		return mv;
	}
	
}
