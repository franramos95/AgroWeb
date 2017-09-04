package com.agroWeb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		mv.addObject("itens", dieta.getItens());
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Dieta dieta, BindingResult result, RedirectAttributes attributes) {

		dieta.setItens(tabelaItensDieta.getItens());
		
		if (result.hasErrors()) {
			return novo(dieta);
		}

		try {
			cadastroDietaService.salvar(dieta);
		} catch (NomeDietaJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(dieta);
		}

		attributes.addFlashAttribute("mensagem", "Dieta cadastrada com sucesso");
		return new ModelAndView("redirect:/dietas/novo");
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
		return mvTabelaItensVenda();
	}

	@PutMapping("/item/{idIngrediente}")
	public ModelAndView alterarQuantidadeItem(@PathVariable Long idIngrediente, Integer quantidade) {
		Ingrediente ingrediente = ingredientesRepository.findOne(idIngrediente);
		tabelaItensDieta.alterarQuantidadeItens(ingrediente, quantidade);
		return mvTabelaItensVenda();

	}

	@DeleteMapping("/item/{idIngrediente}")
	public ModelAndView excluirItem(@PathVariable("idIngrediente") Ingrediente ingrediente) {
		tabelaItensDieta.excluirItem(ingrediente);
		return mvTabelaItensVenda();

	}

	private ModelAndView mvTabelaItensVenda() {
		ModelAndView mv = new ModelAndView("dieta/TabelaIngredientesDieta");
		mv.addObject("itens", tabelaItensDieta.getItens());
		return mv;
	}
}
