package com.agroWeb.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.agroWeb.model.ItemVenda;
import com.agroWeb.model.Produto;

@SessionScope
@Component
public class TabelaItensVenda {

	private List<ItemVenda> itens = new ArrayList<>();
	
	public BigDecimal getValorTotal(){
		return itens.stream()
				.map(ItemVenda::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void adicionarItem(Produto produto, Integer quantidade){
		
		Optional<ItemVenda> itemVendaOptional =  buscarItemPorProduto(produto);
		
		ItemVenda itemVenda = null;
		if(itemVendaOptional.isPresent()){
			itemVenda = itemVendaOptional.get();
			itemVenda.setQuantidade(itemVenda.getQuantidade() + quantidade);
		}else{	
		itemVenda = new ItemVenda();
		itemVenda.setProduto(produto);
		itemVenda.setQuantidade(quantidade);
		itemVenda.setValorUnitario(produto.getValor());
		itens.add(0,itemVenda);			
		}
	
	}
	
	public int total(){
		return itens.size();
	}

	public List<ItemVenda> getItens() {
		return itens;
	}
	
	public void alterarQuantidadeItens(Produto produto, Integer quantidade){
		ItemVenda itemVenda = buscarItemPorProduto(produto).get();
		itemVenda.setQuantidade(null);
	}
	
	public void excluirItem(Produto produto){
		int indice = IntStream.range(0, itens.size())
				.filter(i -> itens.get(i).getProduto().equals(produto))
				.findAny().getAsInt();
		itens.remove(indice);
	}
	
	private Optional<ItemVenda> buscarItemPorProduto(Produto produto){
		Optional<ItemVenda> itemVendaOptional = itens.stream()
				.filter(i -> i.getProduto().equals(produto))
				.findAny();
		return itemVendaOptional;
	}
}
