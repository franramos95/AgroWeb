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
import com.agroWeb.model.Comprador;
import com.agroWeb.model.TipoPessoa;
import com.agroWeb.repository.CompradorRepository;
import com.agroWeb.repository.EstadoRepository;
import com.agroWeb.repository.filter.CompradorFilter;
import com.agroWeb.service.CadastroCompradorService;
import com.agroWeb.service.exception.CpfCnpjClienteJaCadastradoException;
import com.agroWeb.service.exception.ImpossivelExcluirEntidadeException;
import com.agroWeb.service.exception.NomeCompradorJaCadastradoException;

@Controller
@RequestMapping("/compradores")
public class CompradorController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroCompradorService cadastroCompradorService;

	@Autowired
	private CompradorRepository compradorRepository;

	@RequestMapping("/novo")
	public ModelAndView novo(Comprador comprador) {
		ModelAndView mv = new ModelAndView("comprador/CadastroComprador");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("estados", estadoRepository.findAll());
		return mv;
	}

	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Comprador comprador, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(comprador);
		}
		try {
			cadastroCompradorService.salvar(comprador);
		} catch (NomeCompradorJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(comprador);
		} catch (CpfCnpjClienteJaCadastradoException e) {
			result.rejectValue("cpfOuCnp", e.getMessage(), e.getMessage());
			return novo(comprador);
		}

		attributes.addFlashAttribute("mensagem", "Comprador cadastrado com sucesso!");
		return new ModelAndView("redirect:/compradores/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(CompradorFilter filter, BindingResult result,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("comprador/PesquisaComprador");

		PageWrapper<Comprador> pageWrapper = new PageWrapper<>(compradorRepository.filtrar(filter, pageable),
				httpServletRequest);
		mv.addObject("pagina", pageWrapper);
		return mv;
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		try {
			cadastroCompradorService.excluir(id);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Comprador comprador = compradorRepository.buscarComCidadeEstado(id);		
		ModelAndView mv = novo(comprador);
		mv.addObject(comprador);
		return mv;
	}

}
