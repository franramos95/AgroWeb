package com.agroWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.agroWeb.model.Produto;
import com.agroWeb.repository.ProdutoRepository;
import com.agroWeb.session.TabelaItensVenda;

@Controller
@RequestMapping("/vendas")
public class VendaController {
	
	
	@Autowired
	private TabelaItensVenda tabelaItensVenda;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	@GetMapping("/novo")
	public String nova() {
		return "venda/CadastroVenda";
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Long idProduto){
		Produto produto = produtoRepository.findOne(idProduto);
		tabelaItensVenda.adicionarItem(produto, 1);
		ModelAndView mv = new ModelAndView("venda/TabelaItensVenda");
		mv.addObject("itens", tabelaItensVenda.getItens());
		return mv;
	}

}