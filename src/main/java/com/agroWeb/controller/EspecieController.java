package com.agroWeb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import com.agroWeb.model.Especie;
import com.agroWeb.repository.EspecieRepository;
import com.agroWeb.repository.filter.EspecieFilter;
import com.agroWeb.service.CadastroEspecieService;
import com.agroWeb.service.exception.ImpossivelExcluirEntidadeException;
import com.agroWeb.service.exception.NomeEspecieJaCadastradaException;

@Controller
@RequestMapping("/especies")
public class EspecieController {

	@Autowired
	private CadastroEspecieService cadastroEspecieService;

	@Autowired
	private EspecieRepository especieRepository;

	@RequestMapping("/novo")
	public ModelAndView novo(Especie especie) {
		ModelAndView mv = new ModelAndView("especie/CadastroEspecie");
		return mv;
	}

	@RequestMapping(value = {"/novo" , "{\\d+}"}, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Especie especie, BindingResult result, Model model, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(especie);
		}

		try {
			cadastroEspecieService.salvar(especie);
		} catch (NomeEspecieJaCadastradaException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(especie);
		}

		attributes.addFlashAttribute("mensagem", "Especie salva com sucesso!");
		return new ModelAndView("redirect:/especies/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(EspecieFilter filter, BindingResult result,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("especie/PesquisaEspecie");

		PageWrapper<Especie> pageWrapper = new PageWrapper<>(especieRepository.filtrar(filter, pageable),
				httpServletRequest);
		mv.addObject("pagina", pageWrapper);
		return mv;
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		try {
			cadastroEspecieService.excluir(id);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Especie especie = especieRepository.findOne(id);
		ModelAndView mv = novo(especie);
		mv.addObject(especie);
		return mv;
	}

}
