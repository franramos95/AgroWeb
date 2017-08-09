package com.agroWeb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import com.agroWeb.model.Vacina;
import com.agroWeb.repository.VacinaRepository;
import com.agroWeb.repository.filter.VacinaFilter;
import com.agroWeb.service.CadastroVacinaService;
import com.agroWeb.service.exception.ImpossivelExcluirEntidadeException;
import com.agroWeb.service.exception.NomeVacinaJaCadastradaException;

@Controller
@RequestMapping("/vacinas")
public class VacinaController {

	@Autowired
	private CadastroVacinaService cadastroVacinaService;

	@Autowired
	private VacinaRepository vacinaRepository;

	@RequestMapping("/novo")
	public ModelAndView novo(Vacina vacina) {
		ModelAndView mv = new ModelAndView("vacina/CadastroVacina");
		return mv;
	}

	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Vacina vacina, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(vacina);
		}
		try {
			cadastroVacinaService.salva(vacina);
		} catch (NomeVacinaJaCadastradaException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(vacina);
		}
		attributes.addFlashAttribute("mensagem", "Vacina salva com sucesso!");
		return new ModelAndView("redirect:/vacinas/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(VacinaFilter filter, BindingResult result,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("vacina/PesquisaVacina");

		PageWrapper<Vacina> pageWrapper = new PageWrapper<>(vacinaRepository.filtrar(filter, pageable),
				httpServletRequest);
		mv.addObject("pagina", pageWrapper);
		;
		return mv;
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		try {
			cadastroVacinaService.excluir(id);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Vacina vacina = vacinaRepository.findOne(id);
		ModelAndView mv = novo(vacina);
		mv.addObject(vacina);
		return mv;
	}

}
