package com.agroWeb.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.agroWeb.model.Ingrediente;
import com.agroWeb.model.ItemDieta;

@SessionScope
@Component
public class TabelaItensDieta {

	private List<ItemDieta> itens = new ArrayList<>();

	public BigDecimal getValorTotal() {
		// Retorna a soma de todos os itens da lista
		return itens.stream()
				.map(ItemDieta::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void adicionarItem(Ingrediente ingrediente, Integer quantidade){
		Optional<ItemDieta> itemDietaOptional = buscarItemPorIngrediente(ingrediente);
		
		ItemDieta itemDieta = null;
		if (itemDietaOptional.isPresent()){
			itemDieta = itemDietaOptional.get();
			itemDieta.setQuantidade(itemDieta.getQuantidade() + quantidade);
		}else{			
			itemDieta = new ItemDieta();
			itemDieta.setIngrediente(ingrediente);
			itemDieta.setQuantidade(quantidade);
			itemDieta.setValorUnitario(ingrediente.getValorUnitario());
			
			itens.add(0,itemDieta);
		}
		
	}
	
	public int total(){
		return itens.size();
	}

	public List<ItemDieta> getItens() {
		return itens;
	}
	
	public void alterarQuantidadeItens(Ingrediente ingrediente, Integer quantidade){
		ItemDieta itemDieta = buscarItemPorIngrediente(ingrediente).get();
		itemDieta.setQuantidade(quantidade);
		
	}
	
	public void excluirItem(Ingrediente ingrediente) {
		int indice = IntStream.range(0, itens.size())
				.filter(i -> itens.get(i).getIngrediente().equals(ingrediente))
				.findAny().getAsInt();
		itens.remove(indice);
	}
	
	private Optional<ItemDieta> buscarItemPorIngrediente(Ingrediente ingrediente) {
		Optional<ItemDieta> itemDietaOptional = itens.stream()
			.filter(i -> i.getIngrediente().equals(ingrediente))
			.findAny();
		return itemDietaOptional;
	}	

}
