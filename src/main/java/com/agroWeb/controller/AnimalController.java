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
import com.agroWeb.model.Animal;
import com.agroWeb.model.Sexo;
import com.agroWeb.model.SituacaoAnimal;
import com.agroWeb.repository.AnimalRepository;
import com.agroWeb.repository.DietaRepository;
import com.agroWeb.repository.EspecieRepository;
import com.agroWeb.repository.filter.AnimalFilter;
import com.agroWeb.service.CadastroAnimalService;
import com.agroWeb.service.exception.IdBrincoJaCadastradoException;

@Controller
@RequestMapping("/animal")
public class AnimalController {

	@Autowired
	private CadastroAnimalService cadastroAnimalService;

	@Autowired
	private AnimalRepository animalRepository;

	@Autowired
	private DietaRepository dietaRepository;

	@Autowired
	private EspecieRepository especieRepository;

	@RequestMapping("/novo")
	public ModelAndView novo(Animal animal) {
		ModelAndView mv = new ModelAndView("animal/CadastroAnimal");

		mv.addObject("dietas", dietaRepository.findAll());
		mv.addObject("especies", especieRepository.findAll());
		mv.addObject("sexos", Sexo.values());
		mv.addObject("situacoes", SituacaoAnimal.values());

		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Animal animal, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(animal);
		}

		try {
			cadastroAnimalService.salvar(animal);
		} catch (IdBrincoJaCadastradoException e) {
			result.rejectValue("idBrinco", e.getMessage(), e.getMessage());
			return novo(animal);
		}

		attributes.addFlashAttribute("mensagem", "Animal cadastrado com sucesso!");
		return new ModelAndView("redirect:/animal/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(AnimalFilter filter, BindingResult result,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("animal/PesquisaAnimal");

		mv.addObject("dietas", dietaRepository.findAll());
		mv.addObject("especies", especieRepository.findAll());
		mv.addObject("sexos", Sexo.values());
		mv.addObject("situacoes", SituacaoAnimal.values());

		PageWrapper<Animal> pageWrapper = new PageWrapper<>(animalRepository.filtrar(filter, pageable),
				httpServletRequest);
		mv.addObject("pagina", pageWrapper);

		return mv;

	}

}