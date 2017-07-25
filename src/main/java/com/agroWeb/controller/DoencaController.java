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
import com.agroWeb.model.Doenca;
import com.agroWeb.repository.DoencaRepository;
import com.agroWeb.repository.filter.DoencaFilter;
import com.agroWeb.service.CadastroDoencaService;
import com.agroWeb.service.exception.NomeDoencaJaCadastradaException;

@Controller
@RequestMapping("/doenca")
public class DoencaController {

	@Autowired
	private CadastroDoencaService cadastroDoencaService;

	@Autowired
	private DoencaRepository doencaRepository;

	@RequestMapping("/novo")
	public ModelAndView novo(Doenca doenca) {
		ModelAndView mv = new ModelAndView("doenca/CadastroDoenca");
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Doenca doenca, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(doenca);
		}
		try {
			cadastroDoencaService.salva(doenca);
		} catch (NomeDoencaJaCadastradaException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(doenca);
		}
		attributes.addFlashAttribute("mensagem", "Doença salva com sucesso!");
		return new ModelAndView("redirect:/doenca/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(DoencaFilter filter, BindingResult result,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("doenca/PesquisaDoenca");

		PageWrapper<Doenca> pageWrapper = new PageWrapper<>(doencaRepository.filtrar(filter, pageable),
				httpServletRequest);
		mv.addObject("pagina", pageWrapper);
		;
		return mv;
	}

}
