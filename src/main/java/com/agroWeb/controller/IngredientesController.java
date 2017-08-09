package com.agroWeb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agroWeb.controller.page.PageWrapper;
import com.agroWeb.dto.IngredienteDTO;
import com.agroWeb.model.Ingrediente;
import com.agroWeb.repository.IngredientesRepository;
import com.agroWeb.repository.filter.IngredienteFilter;
import com.agroWeb.service.CadastroIngredientesService;
import com.agroWeb.service.exception.ImpossivelExcluirEntidadeException;
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

	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Ingrediente ingrediente, BindingResult result, Model model,
			RedirectAttributes attributes) {
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
	public ModelAndView pesquisar(IngredienteFilter filter, BindingResult result,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("ingrediente/PesquisaIngrediente");

		PageWrapper<Ingrediente> pageWrapper = new PageWrapper<>(ingredientesRepository.filtrar(filter, pageable),
				httpServletRequest);
		mv.addObject("pagina", pageWrapper);
		;
		return mv;
	}

	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<IngredienteDTO> pesquisar(String nome) {
		return ingredientesRepository.porNome(nome);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		try {
			cadastroIngredientesService.excluir(id);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Ingrediente ingrediente = ingredientesRepository.findOne(id);
		ModelAndView mv = novo(ingrediente);
		mv.addObject(ingrediente);
		return mv;
	}

}
