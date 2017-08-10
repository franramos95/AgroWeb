package com.agroWeb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agroWeb.controller.page.PageWrapper;
import com.agroWeb.model.Cidade;
import com.agroWeb.repository.CidadeRepository;
import com.agroWeb.repository.EstadoRepository;
import com.agroWeb.repository.filter.CidadeFilter;
import com.agroWeb.service.CadastroCidadeService;
import com.agroWeb.service.exception.CidadeJaExistenteException;
import com.agroWeb.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/cidades")
public class CidadesController {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	@RequestMapping("/novo")
	public ModelAndView novo(Cidade cidade) {
		ModelAndView mv = new ModelAndView("cidade/CadastroCidades");
		
		mv.addObject("estados",estadoRepository.findAll());
		
		return mv;
	}

	@Cacheable(value = "cidades", key = "#codigoEstado")
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> pesquisarPorCodigoEstado(
			@RequestParam(name = "estado", defaultValue = "-1") Long codigoEstado) {

		return cidadeRepository.findByEstadoCodigo(codigoEstado);
	}
	
	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	@CacheEvict(value = "cidades", key ="#cidade.estado.codigo", condition = "#cidade.temEstado()")
	public ModelAndView salvar(@Valid Cidade cidade, BindingResult result, RedirectAttributes attributes){
		
		if (result.hasErrors()){
			return novo(cidade);
		}
		
		try{
			cadastroCidadeService.salvar(cidade);
		}catch(CidadeJaExistenteException e ){
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(cidade);
		}
		
		attributes.addFlashAttribute("mensagem", "Cidade salva com sucesso!");
		return new ModelAndView("redirect:/cidades/novo");		
		
	}
	
	@GetMapping
	public ModelAndView pesquisar(CidadeFilter filter, BindingResult result, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest){
		ModelAndView mv = new ModelAndView("cidade/PesquisaCidades");
		
		mv.addObject("estados", estadoRepository.findAll());
		
		PageWrapper<Cidade> pageWrapper = new PageWrapper<>(cidadeRepository.filtrar(filter, pageable), httpServletRequest);
		mv.addObject("pagina", pageWrapper);		
		
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Long codigo) {
		try {
			cadastroCidadeService.excluir(codigo);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}	
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Long codigo) {
		Cidade cidade = cidadeRepository.findOne(codigo);
		ModelAndView mv = novo(cidade);
		mv.addObject(cidade);
		return mv;
	}	
}