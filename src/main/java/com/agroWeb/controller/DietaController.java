package com.agroWeb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agroWeb.controller.page.PageWrapper;
import com.agroWeb.model.Dieta;
import com.agroWeb.model.Ingrediente;
import com.agroWeb.repository.DietaRepository;
import com.agroWeb.repository.IngredientesRepository;
import com.agroWeb.repository.filter.DietaFilter;
import com.agroWeb.service.CadastroDietaService;
import com.agroWeb.service.exception.NomeDietaJaCadastradoException;
import com.agroWeb.session.TabelaItensDieta;

@Controller
@RequestMapping("/dietas")
public class DietaController {
	
	@Autowired
	private CadastroDietaService cadastroDietaService;
	
	@Autowired
	private DietaRepository dietaRepository;
	
	@Autowired
	private IngredientesRepository ingredientesRepository;
	
	@Autowired
	private TabelaItensDieta tabelaItensDieta;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Dieta dieta) {
		ModelAndView mv = new ModelAndView("dieta/CadastroDieta");
		mv.addObject("ingredientes", ingredientesRepository.findAll());
		
		return mv;
		}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Dieta dieta, BindingResult result, RedirectAttributes attributes) {
	 
		if(result.hasErrors()){
			return novo(dieta);
					}
		
		try {
			cadastroDietaService.salvar(dieta);
			} catch (NomeDietaJaCadastradoException e){
				result.rejectValue("nome", e.getMessage(), e.getMessage());
				return novo(dieta);
			}
		
		attributes.addFlashAttribute("message","Dieta cadastrada com sucesso");
		return new ModelAndView("redirect:/dieta/novo");
	}
	
	public ModelAndView pesquisar(@Valid DietaFilter filter, BindingResult result,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("dieta/PesquisaDieta");

		mv.addObject("ingredientes", ingredientesRepository.findAll());
		
		PageWrapper<Dieta> pageWrapper = new PageWrapper<>(dietaRepository.filtrar(filter, pageable),
				httpServletRequest);
		mv.addObject("pagina", pageWrapper);

		return mv;

	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Long idIngrediente) {
		
		Ingrediente ingrediente = ingredientesRepository.findOne(idIngrediente);
		tabelaItensDieta.adicionarItem(ingrediente, 1);
		ModelAndView mv = new ModelAndView("dieta/TabelaIngredientesDieta");
		mv.addObject("itens", tabelaItensDieta.getItens());
		return mv;
	}
	
}
