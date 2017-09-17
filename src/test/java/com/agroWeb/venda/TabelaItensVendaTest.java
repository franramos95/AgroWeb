package com.agroWeb.venda;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.agroWeb.model.Produto;
import com.agroWeb.session.TabelaItensVenda;

public class TabelaItensVendaTest {

	private TabelaItensVenda tabelaItensVenda;
	
	@Before
	public void setUp(){
		this.tabelaItensVenda = new TabelaItensVenda();
	}
	
	@Test
	public void deveCalcularValorTotalSemItens() throws Exception {
		assertEquals(BigDecimal.ZERO, tabelaItensVenda.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComUmItem() throws Exception {
		Produto produto = new Produto();
		BigDecimal valor = new BigDecimal("100");
		produto.setValor(valor);
		
		tabelaItensVenda.adicionarItem(produto, 1);
		
		assertEquals(valor, tabelaItensVenda.getValorTotal());		
	}
	
	@Test
		public void deveCalcularValorTotalComMaisDeUmItem() throws Exception {
		Produto produto1 = new Produto();
		produto1.setId(1L);
		BigDecimal valor1 = new BigDecimal("100");
		produto1.setValor(valor1);
		
		Produto produto2 = new Produto();
		produto2.setId(2L);
		BigDecimal valor2 = new BigDecimal("200");
		produto2.setValor(valor2);
		
		tabelaItensVenda.adicionarItem(produto1, 1);
		tabelaItensVenda.adicionarItem(produto2, 2);
		
		assertEquals(new BigDecimal("500"), tabelaItensVenda.getValorTotal());			
	}
	
	@Test
	public void deveManterTamanhoDaListaParaMesmosProdutos() throws Exception {
		Produto p3 = new Produto();
		p3.setId(1L);
		p3.setValor(new BigDecimal("100.50"));
		
		tabelaItensVenda.adicionarItem(p3, 1);
		tabelaItensVenda.adicionarItem(p3, 1);
		
		assertEquals(1, tabelaItensVenda.total());
		assertEquals(new BigDecimal("201.00"), tabelaItensVenda.getValorTotal());
	}
}
