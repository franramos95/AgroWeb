package com.agroWeb.service.event.dieta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.agroWeb.model.Ingrediente;
import com.agroWeb.model.ItemDieta;
import com.agroWeb.repository.IngredientesRepository;

@Component
public class DietaListener {

	@Autowired
	private IngredientesRepository ingredientesRepository;
	
	@EventListener
	public void dietaRealizada(DietaEvent dietaEvent){
		for(ItemDieta item : dietaEvent.getDieta().getItens()){
			Ingrediente ingrediente = ingredientesRepository.findOne(item.getIngrediente().getId());
			ingrediente.setQuantidade(ingrediente.getQuantidade() - item.getQuantidade());
			ingredientesRepository.save(ingrediente);
		}
	}
}
