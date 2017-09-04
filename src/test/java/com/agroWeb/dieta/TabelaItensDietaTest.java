package com.agroWeb.dieta;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.agroWeb.model.Ingrediente;
import com.agroWeb.session.TabelaItensDieta;

public class TabelaItensDietaTest {
	
	private TabelaItensDieta tabelaItensDieta;
	
	@Before
	public void setUp(){
		this.tabelaItensDieta = new TabelaItensDieta();
	}
	
	@Test
	public void deveCalcularValorTotalSemItens() throws Exception {
		assertEquals(BigDecimal.ZERO, tabelaItensDieta.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComUmItem() throws Exception {
		Ingrediente ingrediente = new Ingrediente();
		BigDecimal valor = new BigDecimal("8.90");
		ingrediente.setValorUnitario(valor);
		
		tabelaItensDieta.adicionarItem(ingrediente, 1);
		
		assertEquals(valor,tabelaItensDieta.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComVariosItens() throws Exception {

		Ingrediente i1 = new Ingrediente();
		i1.setId(1l);
		BigDecimal v1 = new BigDecimal("8.90");
		i1.setValorUnitario(v1);
		
		Ingrediente i2 = new Ingrediente();
		i2.setId(2l);
		BigDecimal v2 = new BigDecimal("4.99");
		i2.setValorUnitario(v2);
		
		tabelaItensDieta.adicionarItem(i1,1);
		tabelaItensDieta.adicionarItem(i2,2);
		
		assertEquals(new BigDecimal("18.88"),tabelaItensDieta.getValorTotal());
	}
	
	@Test
	public void deveManterTamanhoDaListaPataMesmosItens() throws Exception {
		Ingrediente i1 = new Ingrediente();
		i1.setId(1l);
		i1.setValorUnitario(new BigDecimal("4.50"));
		
		tabelaItensDieta.adicionarItem(i1,1);
		tabelaItensDieta.adicionarItem(i1,1);
		
		assertEquals(1, tabelaItensDieta.total());
		assertEquals(new BigDecimal("9.00"), tabelaItensDieta.getValorTotal());
	}
	
	@Test
	public void deveAlterarQuantidadeItem() throws Exception {
		
		Ingrediente i1 = new Ingrediente();
		i1.setId(1l);
		i1.setValorUnitario(new BigDecimal("4.50"));
		
		tabelaItensDieta.adicionarItem(i1, 1);
		tabelaItensDieta.alterarQuantidadeItens(i1, 3);
		
		assertEquals(1, tabelaItensDieta.total());
		assertEquals(new BigDecimal("13.50"), tabelaItensDieta.getValorTotal());
		
	}

	@Test
	public void deveExcluirItem() throws Exception {
		
		Ingrediente i1 = new Ingrediente();
		i1.setId(1l);
		i1.setValorUnitario(new BigDecimal("8.90"));
		
		Ingrediente i2 = new Ingrediente();
		i2.setId(2l);
		i2.setValorUnitario(new BigDecimal("4.99"));
		
		Ingrediente i3 = new Ingrediente();
		i3.setId(3l);
		i3.setValorUnitario(new BigDecimal("2.00"));
		
		tabelaItensDieta.adicionarItem(i1, 1);
		tabelaItensDieta.adicionarItem(i2, 2);
		tabelaItensDieta.adicionarItem(i3, 1);
		
		tabelaItensDieta.excluirItem(i2);
		
		assertEquals(2, tabelaItensDieta.total());
		assertEquals(new BigDecimal("10.90"), tabelaItensDieta.getValorTotal());
		
	}
	
}
