package com.agroWeb.controller;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.agroWeb.controller.page.PageWrapper;
import com.agroWeb.model.Produto;
import com.agroWeb.repository.ProdutoRepository;
import com.agroWeb.repository.filter.ProdutoFilter;
import com.agroWeb.service.CadastroProdutoService;
import com.agroWeb.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Produto produto){
		ModelAndView mv = new ModelAndView("produto/CadastroProduto");
		
		return mv;
	}
	
	@GetMapping
	public ModelAndView pesquisar(ProdutoFilter filter, BindingResult result,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("produto/Pesquisaproduto");

		PageWrapper<Produto> pageWrapper = new PageWrapper<>(produtoRepository.filtrar(filter, pageable),
				httpServletRequest);
		mv.addObject("pagina", pageWrapper);
		return mv;
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		try {
			cadastroProdutoService.excluir(id);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

}
