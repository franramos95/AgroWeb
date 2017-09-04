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
import com.agroWeb.model.Despesa;
import com.agroWeb.model.TipoDespesa;
import com.agroWeb.repository.DespesaRepository;
import com.agroWeb.repository.filter.DespesaFilter;
import com.agroWeb.service.CadastroDespesaService;
import com.agroWeb.service.exception.ImpossivelExcluirEntidadeException;
import com.agroWeb.service.exception.NomeDespesaJaCadastradoException;

@Controller
@RequestMapping("/despesas")
public class DespesaController {

	@Autowired
	private CadastroDespesaService cadastroDespesaService;

	@Autowired
	private DespesaRepository despesaRepository;

	@RequestMapping("/novo")
	public ModelAndView novo(Despesa despesa) {
		ModelAndView mv = new ModelAndView("despesa/CadastroDespesa");
		
		mv.addObject("tipoDespesas", TipoDespesa.values());
		return mv;
	}

	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)	
	public ModelAndView salvar(@Valid Despesa despesa, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(despesa);
		}
		try {
			cadastroDespesaService.salva(despesa);
		} catch (NomeDespesaJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(despesa);
		}
		attributes.addFlashAttribute("mensagem", "Despesa salva com sucesso");
		return new ModelAndView("redirect:/despesas/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(DespesaFilter filter, BindingResult result,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("despesa/PesquisaDespesa");

				
		PageWrapper<Despesa> pageWrapper = new PageWrapper<>(despesaRepository.filtrar(filter, pageable),
				httpServletRequest);
		mv.addObject("pagina", pageWrapper);
		mv.addObject("tipoDespesas", TipoDespesa.values());
		return mv;
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		try {
			cadastroDespesaService.excluir(id);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Despesa despesa = despesaRepository.findOne(id);
		ModelAndView mv = novo(despesa);
		mv.addObject(despesa);
		return mv;
	}

}
