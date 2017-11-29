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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agroWeb.controller.page.PageWrapper;
import com.agroWeb.model.Produto;
import com.agroWeb.model.StatusVenda;
import com.agroWeb.model.Venda;
import com.agroWeb.repository.AnimalRepository;
import com.agroWeb.repository.CompradorRepository;
import com.agroWeb.repository.ProdutoRepository;
import com.agroWeb.repository.VendaRepository;
import com.agroWeb.repository.filter.VendaFilter;
import com.agroWeb.service.CadastroVendaService;
import com.agroWeb.service.exception.ImpossivelExcluirEntidadeException;
import com.agroWeb.session.TabelaItensVenda;

@Controller
@RequestMapping("/vendas")
public class VendaController {

	@Autowired
	private TabelaItensVenda tabelaItensVenda;

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private CadastroVendaService cadastroVendaService;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CompradorRepository compradorRepository;

	@Autowired
	private AnimalRepository animalRepository;

	@RequestMapping("/novo")
	public ModelAndView novo(Venda venda) {
		ModelAndView mv = new ModelAndView("venda/CadastroVenda");
		mv.addObject("compradores", compradorRepository.findAll());
		mv.addObject("animais", animalRepository.findAll());
		mv.addObject("situacao", StatusVenda.values());

		return mv;
	}

	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Venda venda, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(venda);
		}

		cadastroVendaService.salva(venda);

		attributes.addFlashAttribute("mensagem", "Venda cadastrado com sucesso!");
		return new ModelAndView("redirect:/vendas/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(@Valid VendaFilter filter, BindingResult result,
			@PageableDefault(size = 20) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("venda/PesquisaVenda");
		mv.addObject("produto", produtoRepository.findAll());

		PageWrapper<Venda> pageWrapper = new PageWrapper<>(vendaRepository.filtrar(filter, pageable),
				httpServletRequest);
		mv.addObject("pagina", pageWrapper);
		return mv;
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		try {
			cadastroVendaService.excluir(id);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Venda venda = vendaRepository.findOne(id);
		ModelAndView mv = novo(venda);
		mv.addObject(venda);
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

}