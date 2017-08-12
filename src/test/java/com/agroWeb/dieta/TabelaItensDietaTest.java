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
		ingrediente.setValor(valor);
		
		tabelaItensDieta.adicionarItem(ingrediente, 1);
		
		assertEquals(valor,tabelaItensDieta.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComVariosItens() throws Exception {

		Ingrediente i1 = new Ingrediente();
		i1.setId(1l);
		BigDecimal v1 = new BigDecimal("8.90");
		i1.setValor(v1);
		
		Ingrediente i2 = new Ingrediente();
		i2.setId(2l);
		BigDecimal v2 = new BigDecimal("4.99");
		i2.setValor(v2);
		
		tabelaItensDieta.adicionarItem(i1,1);
		tabelaItensDieta.adicionarItem(i2,2);
		
		assertEquals(new BigDecimal("18.88"),tabelaItensDieta.getValorTotal());
	}
	
	@Test
	public void deveManterTamanhoDaListaPataMesmosItens() throws Exception {
		Ingrediente i1 = new Ingrediente();
		i1.setId(1l);
		i1.setValor(new BigDecimal("4.50"));
		
		tabelaItensDieta.adicionarItem(i1,1);
		tabelaItensDieta.adicionarItem(i1,1);
		
		assertEquals(1, tabelaItensDieta.total());
		assertEquals(new BigDecimal("9.00"), tabelaItensDieta.getValorTotal());
	}

}
