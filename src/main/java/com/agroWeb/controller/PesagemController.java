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
import com.agroWeb.model.Pesagem;
import com.agroWeb.repository.PesagemRepository;
import com.agroWeb.repository.filter.PesagemFilter;
import com.agroWeb.service.CadastroPesagemService;

@Controller
@RequestMapping("/pesagem")
public class PesagemController {
	
	@Autowired
	private CadastroPesagemService cadastroPesagemService;

	@Autowired
	private PesagemRepository pesagemRepository;

	@RequestMapping("/novo")
	public ModelAndView novo(Pesagem pesagem) {
		ModelAndView mv = new ModelAndView("pesagem/CadastroPesagem");
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Pesagem pesagem, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(pesagem);
		}
		cadastroPesagemService.salva(pesagem);
		attributes.addFlashAttribute("mensagem", "Pesagem salva com sucesso!");
		return new ModelAndView("redirect:/pesagem/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(PesagemFilter filter, BindingResult result, @PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest){
		ModelAndView mv = new ModelAndView("pesagem/PesquisaPesagem");
	
		PageWrapper<Pesagem> pageWrapper = new PageWrapper<>(pesagemRepository.filtrar(filter, pageable), httpServletRequest);
		mv.addObject("pagina", pageWrapper);;
		return mv;
	}


}
