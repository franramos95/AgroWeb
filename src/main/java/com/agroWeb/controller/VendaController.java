package com.agroWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.agroWeb.model.Produto;
import com.agroWeb.model.Venda;
import com.agroWeb.repository.CompradorRepository;
import com.agroWeb.repository.ProdutoRepository;
import com.agroWeb.session.TabelaItensVenda;

@Controller
@RequestMapping("/vendas")
public class VendaController {

	@Autowired
	private TabelaItensVenda tabelaItensVenda;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CompradorRepository compradorRepository;

	@GetMapping("/novo")
	public ModelAndView novo(Venda venda) {
		ModelAndView mv = new ModelAndView("venda/CadastroVenda");

		mv.addObject("compradores", compradorRepository.findAll());

		return mv;
	}

	@PostMapping("/item")
	public ModelAndView adicionarItem(Long idProduto) {
		Produto produto = produtoRepository.findOne(idProduto);
		tabelaItensVenda.adicionarItem(produto, 1);
		ModelAndView mv = new ModelAndView("venda/TabelaItensVenda");
		mv.addObject("itens", tabelaItensVenda.getItens());
		return mv;
	}

	@PutMapping("/item/{idProduto}")
	public ModelAndView alterarQuantidadeItem(@PathVariable Long idProduto, Integer quantidade) {
		Produto produto = produtoRepository.findOne(idProduto);
		tabelaItensVenda.alterarQuantidadeItens(produto, quantidade);
		ModelAndView mv = new ModelAndView("venda/TabelaItensVenda");
		mv.addObject("itens", tabelaItensVenda.getItens());
		return mv;
	}

}