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
import com.agroWeb.model.Doenca;
import com.agroWeb.repository.DoencaRepository;
import com.agroWeb.repository.filter.DoencaFilter;
import com.agroWeb.service.CadastroDoencaService;
import com.agroWeb.service.exception.ImpossivelExcluirEntidadeException;
import com.agroWeb.service.exception.NomeDoencaJaCadastradaException;

@Controller
@RequestMapping("/doencas")
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

	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Doenca doenca, BindingResult result, Model model, RedirectAttributes attributes) {
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
		return new ModelAndView("redirect:/doencas/novo");
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

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		try {
			cadastroDoencaService.excluir(id);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public ModelAndView edita(@PathVariable("id") Long id) {
		Doenca doenca = doencaRepository.findOne(id);
		ModelAndView mv = novo(doenca);
		mv.addObject(doenca);
		return mv;
	}
}
